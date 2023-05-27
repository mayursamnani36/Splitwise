package com.gamechanger.splitwise.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamechanger.splitwise.entity.UserEntity;
import com.gamechanger.splitwise.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public List<UserEntity> fetchUserList() {
        return userRepository.findAll();
    }

    @Override
    public List<UserEntity> fetchUserById(Long userId) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
        return optionalUserEntity.map(Collections::singletonList).orElse(Collections.emptyList());
    }
    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}
