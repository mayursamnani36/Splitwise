package com.gamechanger.splitwise.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gamechanger.splitwise.entity.GroupEntity;
import com.gamechanger.splitwise.service.GroupService;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.gamechanger.splitwise.constants.SplitwiseConstants.*;
import static com.gamechanger.splitwise.constants.SplitwiseConstants.ID;

@RestController
public class GroupController {

    @Autowired
    GroupService groupService;

    @PostMapping(GROUP)
    public GroupEntity saveGroup(@RequestBody GroupEntity group) {
        List<Pair<Long, Long>> emptyList = new ArrayList<>();
        group.setUserBalanceList(emptyList);
        return groupService.saveGroup(group);
    }

    @GetMapping(GROUP)
    public List<GroupEntity> fetch(
            @RequestParam(value = ID, required = false, defaultValue = "-1") Long groupId
    ) {
        if(groupId!=-1){return groupService.fetchGroupById(groupId);}
        else{return groupService.fetchGroupList();}
    }

    @PostMapping(ADD_USER)
    public String addUserToGroup(@RequestBody String jsonString) throws JsonProcessingException {
        return groupService.addUserToGroup(jsonString);
    }
}
