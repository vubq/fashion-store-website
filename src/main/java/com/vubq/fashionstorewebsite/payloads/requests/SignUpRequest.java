package com.vubq.fashionstorewebsite.payloads.requests;

import com.vubq.fashionstorewebsite.entities.Role;
import com.vubq.fashionstorewebsite.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    private String userName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private String firstName;
    private String lastName;
    private String avatar;
    private List<ERole> roles;
}
