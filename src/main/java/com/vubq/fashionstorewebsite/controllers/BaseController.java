package com.vubq.fashionstorewebsite.controllers;

import com.vubq.fashionstorewebsite.entities.User;
import com.vubq.fashionstorewebsite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class BaseController {

    @Autowired
    protected UserService userService;

    protected User getUserById(String userId) {
        return this.userService.findById(userId).orElse(null);
    }
}
