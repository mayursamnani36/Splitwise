package com.gamechanger.splitwise.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamechanger.splitwise.entity.GroupEntity;
import com.gamechanger.splitwise.entity.UserEntity;
import com.gamechanger.splitwise.repository.GroupRepository;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService{
    @Autowired
    UserService userService;
    @Autowired
    GroupRepository groupRepository;

    public GroupEntity saveGroup(GroupEntity group){
        return groupRepository.save(group);
    }

    @Override
    public List<GroupEntity> fetchGroupById(Long groupId) {
        List<GroupEntity> groupList = new ArrayList<>();
        groupRepository.findById(groupId).ifPresent(groupList::add);
        return groupList;
    }


    @Override
    public String addUserToGroup(String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonString);
        Long userId = jsonNode.get("userId").asLong();
        Long groupId = jsonNode.get("groupId").asLong();

        UserEntity user = userService.fetchUserById(userId).get(0);
        GroupEntity group = fetchGroupById(groupId).get(0);

        List<Long> userGroupList = user.getGroupList();
        userGroupList.add(groupId);
        user.setGroupList(userGroupList);
        userService.saveUser(user);

        List<Pair<Long, Long>> groupUserBalanceList = group.getUserBalanceList();
        groupUserBalanceList.add(new Pair<>(userId, 0L));
        group.setUserBalanceList(groupUserBalanceList);
        saveGroup(group);

        return user.getUserName() + " added to group " + group.getGroupName();
    }

    @Override
    public List<GroupEntity> fetchGroupList() {
        return groupRepository.findAll();
    }
}
