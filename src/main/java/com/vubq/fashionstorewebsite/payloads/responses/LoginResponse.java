package com.vubq.fashionstorewebsite.payloads.responses;

import com.vubq.fashionstorewebsite.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {

    private String token;
    private String type = "Bearer";
    private LoginUserResponse user;
}