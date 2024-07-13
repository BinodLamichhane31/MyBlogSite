package com.example.myblogsite.shared.pojo;

import com.example.myblogsite.entity.User;
import com.example.myblogsite.pojo.UserPojo;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@NoArgsConstructor

public class JwtAuthResponse {
    private String token;
    private UserPojo user;
}
