package com.vubq.fashionstorewebsite.payloads.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginUserResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String avatar;
    private List<String> roles;
}
