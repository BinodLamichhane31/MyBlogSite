package com.example.myblogsite.service;

import com.example.myblogsite.pojo.UserPojo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    UserPojo createUser(UserPojo userPojo);
    UserPojo getUserById(Long userId);
    List<UserPojo> getAllUsers();
    UserPojo updateUser(UserPojo userPojo,Long userId);
    void deleteUser(Long userId);
    public void updatePasswords();

}
