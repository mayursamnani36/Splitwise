package com.gamechanger.splitwise.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gamechanger.splitwise.entity.GroupEntity;

import java.util.List;

public interface GroupService {
    public GroupEntity saveGroup(GroupEntity group);

    public List<GroupEntity> fetchGroupById(Long groupId);

    public String addUserToGroup(String jsonString) throws JsonProcessingException;

    public List<GroupEntity> fetchGroupList();
}
