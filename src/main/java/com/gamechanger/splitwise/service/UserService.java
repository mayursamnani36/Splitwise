package com.gamechanger.splitwise.service;

import java.util.List;

import com.gamechanger.splitwise.entity.UserEntity;

public interface UserService {

    public UserEntity saveUser(UserEntity user);

    public List<UserEntity> fetchUserList();

    public List<UserEntity> fetchUserById(Long userId);

    public void deleteUserById(Long userId);
}
