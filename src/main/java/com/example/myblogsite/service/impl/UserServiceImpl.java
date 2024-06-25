package com.example.myblogsite.service.impl;

import com.example.myblogsite.entity.User;
import com.example.myblogsite.exception.ResourceNotFoundException;
import com.example.myblogsite.pojo.UserPojo;
import com.example.myblogsite.repository.UserRepository;
import com.example.myblogsite.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserPojo createUser(UserPojo userPojo) {
        User user = this.userPojoToUser(userPojo);
        User savedUser = this.userRepository.save(user);
        return this.userToUserPojo(savedUser);
    }

    @Override
    public UserPojo getUserById(Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()->
                new ResourceNotFoundException("User","id",userId));
        return userToUserPojo(user);
    }

    @Override
    public List<UserPojo> getAllUsers() {
        List<User> users=this.userRepository.findAll();
        return users.stream().map(user -> this.userToUserPojo(user)).collect(Collectors.toList());

    }

    @Override
    public UserPojo updateUser(UserPojo userPojo, Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User","id",userId));

        user.setName(userPojo.getName());
        user.setEmail(userPojo.getEmail());
        user.setPassword(userPojo.getPassword());
        user.setAbout(userPojo.getAbout());

        User updatedUser = this.userRepository.save(user);

        return this.userToUserPojo(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User","id",userId));
        this.userRepository.delete(user);
    }

    public User userPojoToUser(UserPojo userPojo) {
        User user = this.modelMapper.map(userPojo, User.class);
//        user.setId(userPojo.getId());
//        user.setName(userPojo.getName());
//        user.setEmail(userPojo.getEmail());
//        user.setPassword(userPojo.getPassword());
//        user.setAbout(userPojo.getAbout());
        return user;

    }

    public UserPojo userToUserPojo(User user) {
        UserPojo userPojo = this.modelMapper.map(user, UserPojo.class);
//        userPojo.setId(user.getId());
//        userPojo.setName(user.getName());
//        userPojo.setEmail(user.getEmail());
//        userPojo.setPassword(user.getPassword());
//        userPojo.setAbout(user.getAbout());
        return userPojo;
    }
}
