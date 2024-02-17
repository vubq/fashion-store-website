package com.vubq.fashionstorewebsite.services;

import com.vubq.fashionstorewebsite.entities.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUserName(String userName);
    Boolean existsByUserName(String userName);
    Boolean existsByEmail(String email);

    Optional<User> findById(String id);

    User create(User user);
}