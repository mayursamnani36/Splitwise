package com.gamechanger.splitwise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gamechanger.splitwise.entity.UserEntity;
import com.gamechanger.splitwise.service.UserService;

import static com.gamechanger.splitwise.constants.SplitwiseConstants.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(USER_PATH)
    public UserEntity saveUser(@RequestBody UserEntity user) {
        return userService.saveUser(user);
    }

    @GetMapping(USER_PATH)
    public List<UserEntity> fetch(
            @RequestParam(value = ID, required = false, defaultValue = "-1") Long userId,
            @RequestParam(value = GROUP, required = false, defaultValue = "") String userGroup
    ) {
        if(userId!=-1){return userService.fetchUserById(userId);}
        else if(!userGroup.isEmpty()){return userService.fetchUsersByGroup(userGroup);}
        else{return userService.fetchUserList();}
    }

     @DeleteMapping(USER_PATH)
     public String delete(
             @RequestParam(value = ID, required = false, defaultValue = "-1") Long userId,
             @RequestParam(value = GROUP, required = false, defaultValue = "") String userGroup
     ) {
         if(userId!=-1){
             userService.deleteUserById(userId);
             return USER_DELETED;
         }
         else if(!userGroup.isEmpty()){
             userService.deleteUsersByGroup(userGroup);
             return GROUP_DELETED;
         }
         return INVALID_PATH_PARAM;
     }

    // @PutMapping("/users/{id}/{amount}")
    // public String updateBalance(@PathParam("amount") Long amount,
    // @PathParam("id") Long userId) {
    // userService.updateBalance(amount, userId);
    // return "Balances have been updated";
    // }
}
