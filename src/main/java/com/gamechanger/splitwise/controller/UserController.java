package com.gamechanger.splitwise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gamechanger.splitwise.entity.UserEntity;
import com.gamechanger.splitwise.service.UserService;

import jakarta.websocket.server.PathParam;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/users")
    public UserEntity saveUser(@RequestBody UserEntity user) {
        return userService.saveUser(user);
    }

    @GetMapping("/users")
    public List<UserEntity> fetchUserList() {
        return userService.fetchUserList();
    }

    @GetMapping("/users/{id}")
    public UserEntity fetchUserById(@PathParam("id") Long userId) {
        return userService.fetchUserById(userId);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUserById(@PathParam("id") Long userId) {
        userService.deleteUserById(userId);
        return "User Deleted Successfully";
    }
}
