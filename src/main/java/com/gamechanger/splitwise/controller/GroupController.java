package com.gamechanger.splitwise.controller;

import com.gamechanger.splitwise.entity.GroupEntity;
import com.gamechanger.splitwise.service.GroupServiceImpl;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

import static com.gamechanger.splitwise.constants.SplitwiseConstants.GROUP;

@RestController
public class GroupController {

    @Autowired
    GroupServiceImpl groupService;

    @PostMapping(GROUP)

    public GroupEntity saveGroup(@RequestBody GroupEntity group){
//        List<AbstractMap.SimpleEntry<Long,Long>> emptyList = new ArrayList<>();
        List<Pair<Long, Long>> emptyList = new ArrayList<>();
        group.setUserBalanceList(emptyList);
        return groupService.saveGroup(group);
    }

}
