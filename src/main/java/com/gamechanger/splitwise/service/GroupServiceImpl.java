package com.gamechanger.splitwise.service;

import com.gamechanger.splitwise.entity.GroupEntity;
import com.gamechanger.splitwise.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService{

    @Autowired
    GroupRepository groupRepository;
    public GroupEntity saveGroup(GroupEntity group){
        return (GroupEntity) groupRepository.save(group);

    }
}
