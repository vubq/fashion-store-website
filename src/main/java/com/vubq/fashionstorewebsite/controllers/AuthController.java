package com.vubq.fashionstorewebsite.controllers;

import com.vubq.fashionstorewebsite.config.security.jwt.JwtUtils;
import com.vubq.fashionstorewebsite.config.security.service.UserDetailsImpl;
import com.vubq.fashionstorewebsite.entities.Role;
import com.vubq.fashionstorewebsite.entities.User;
import com.vubq.fashionstorewebsite.enums.ERole;
import com.vubq.fashionstorewebsite.payloads.requests.SignInRequest;
import com.vubq.fashionstorewebsite.payloads.requests.SignUpRequest;
import com.vubq.fashionstorewebsite.payloads.responses.LoginResponse;
import com.vubq.fashionstorewebsite.payloads.responses.LoginUserResponse;
import com.vubq.fashionstorewebsite.services.RoleService;
import com.vubq.fashionstorewebsite.services.UserService;
import com.vubq.fashionstorewebsite.payloads.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/webapi/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/sign-in")
    public Response authenticateUser(@RequestBody SignInRequest signInRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUserName(), signInRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        User user = this.userService.findById(userDetails.getId()).orElse(null);

        return Response.build().ok()
                .data(LoginResponse.builder()
                        .token(jwt)
                        .type("Bearer")
                        .user(LoginUserResponse.builder()
                                .id(user.getId())
                                .firstName(user.getFirstName())
                                .lastName(user.getLastName())
                                .avatar(user.getAvatar())
                                .roles(roles)
                                .build())
                        .build());
    }

    @PostMapping("/sign-up")
    public Response registerUser(@RequestBody SignUpRequest signUpRequest) {
        if (userService.existsByUserName(signUpRequest.getUserName())) {
            return Response.build().ok().data("Error: Username is already taken!");
        }

        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return Response.build().ok().data("Error: Email is already in use!");
        }

        User user = User.builder()
                .userName(signUpRequest.getUserName())
                .email(signUpRequest.getEmail())
                .password(this.encoder.encode(signUpRequest.getPassword()))
                .phoneNumber(signUpRequest.getPhoneNumber())
                .address(signUpRequest.getAddress())
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .avatar(signUpRequest.getAvatar())
                .build();
        Set<Role> roles = new HashSet<>();
        this.roleService.getListRoleByListRoleName(Arrays.asList(ERole.CUSTOMER)).forEach(role -> roles.add(role));
        user.setRoles(roles);

        this.userService.create(user);
        return Response.build().ok().data("Create user success!");
    }
}

