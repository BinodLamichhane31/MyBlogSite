package com.example.myblogsite.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserPojo {
     private Long id;
     private String name;
     private String email;
     private String password;
     private String about;
}
