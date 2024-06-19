package com.example.myblogsite.service;

import com.example.myblogsite.pojo.UserPojo;

import java.util.List;

public interface UserService {
    UserPojo createUser(UserPojo userPojo);
    UserPojo getUserById(Long userId);
    List<UserPojo> getAllUsers();
    UserPojo updateUser(UserPojo userPojo,Long userId);
    void deleteUser(Long userId);

}
