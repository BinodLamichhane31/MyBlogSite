package com.example.myblogsite.controller;

import com.example.myblogsite.pojo.UserPojo;
import com.example.myblogsite.service.UserService;
import com.example.myblogsite.shared.pojo.ApiResponse;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private  UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserPojo> createUser(@RequestBody UserPojo userPojo){
        UserPojo createdUserPojo = this.userService.createUser(userPojo);
        return new ResponseEntity<>(createdUserPojo, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserPojo>> getUser(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserPojo> getSingleUser(@PathVariable Long userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserPojo> updateUser(@RequestBody UserPojo userPojo,@PathVariable Long userId){
        UserPojo updatedUserPojo = this.userService.updateUser(userPojo,userId);
        return ResponseEntity.ok(updatedUserPojo);
    }

    @DeleteMapping("/{userID}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userID){
        this.userService.deleteUser(userID);
        return new ResponseEntity<>(new ApiResponse("User deleted successfully.",true),HttpStatus.OK);
    }

}
