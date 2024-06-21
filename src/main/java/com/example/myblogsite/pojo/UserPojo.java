package com.example.myblogsite.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

     @NotEmpty
     @Size(min = 6, max = 30,message = "Username must be minimum of 4 characters and maximum of 30 characters.")
     private String name;
     @Email(message = "Email address is not valid.")
     private String email;
     @NotEmpty
     @Size(min = 6, max = 12,message = "Password must be of min of 6 characters and max of 12 characters.")
     private String password;
     @NotEmpty
     private String about;
}
