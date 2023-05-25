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
    public List<UserEntity> fetchUsersByGroup(String userGroup) {
        return userRepository.findByGroupName(userGroup);
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void deleteUsersByGroup(String userGroup) {
        userRepository.deleteByGroupName(userGroup);
    }

    @Override
    public void updateBalance(Long amount, Long userId) {
        UserEntity user = userRepository.findById(userId).get();
        String userGroup = user.getGroupName();
        List<UserEntity> usersWithSameGroup = userRepository.findByGroupName(userGroup);
        Long groupSize = (long) usersWithSameGroup.size();
        Long amountPerHead = amount / groupSize;
        for (UserEntity person : usersWithSameGroup) {
            Long curBalance = person.getBalance();
            if (person.getId() == userId) {
                curBalance += (amount - amountPerHead);
                person.setBalance(curBalance);
            } else {
                curBalance -= amountPerHead;
                person.setBalance(curBalance);
            }
        }
    }

}
