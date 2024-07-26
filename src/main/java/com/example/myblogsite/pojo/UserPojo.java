package com.example.myblogsite.pojo;

import com.example.myblogsite.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserPojo {
     private Long id;

     @NotEmpty
     @Size(min = 4, max = 30,message = "Username must be minimum of 4 characters and maximum of 30 characters.")
     private String name;

     @NotEmpty
     @Email(message = "Email address is not valid.")
     private String email;

//     @NotEmpty
     @Size(min = 6, max = 12,message = "Password must be of min of 6 characters and max of 12 characters.")
     private String password;
     @NotEmpty
     private String about;
     private Set<RolePojo> roles = new HashSet<>();

     @JsonIgnore
     public String getPassword() {
          return this.password;
     }

     @JsonProperty
     public void setPassword(String password) {
          this.password = password;
     }

}
