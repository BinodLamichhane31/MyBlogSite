package com.example.myblogsite.controller;

import com.example.myblogsite.pojo.UserPojo;
import com.example.myblogsite.service.UserService;
import com.example.myblogsite.shared.pojo.ApiResponse;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private  UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserPojo> createUser(@Valid @RequestBody UserPojo userPojo){
        UserPojo createdUserPojo = this.userService.createUser(userPojo);
        return new ResponseEntity<>(createdUserPojo, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserPojo>> getUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserPojo> getSingleUser(@PathVariable Long userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserPojo> updateUser(@Valid @RequestBody UserPojo userPojo, @PathVariable Long userId){
        System.out.println("Received payload for update: " + userPojo.getAbout()+ userPojo.getEmail());
        UserPojo updatedUserPojo = this.userService.updateUser(userPojo, userId);
        return ResponseEntity.ok(updatedUserPojo);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userID}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userID){
        this.userService.deleteUser(userID);
        return new ResponseEntity<>(new ApiResponse("User deleted successfully.",true),HttpStatus.OK);
    }

    @GetMapping("/check-email")
    public ResponseEntity<Map<String, Boolean>> checkEmail(@RequestParam String email) {
        boolean exists = userService.doesEmailExist(email);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }

}
