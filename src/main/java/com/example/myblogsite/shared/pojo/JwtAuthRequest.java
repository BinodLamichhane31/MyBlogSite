package com.example.myblogsite.shared.pojo;

import lombok.Data;

@Data
public class JwtAuthRequest {
    private String username;
    private String password;
}
