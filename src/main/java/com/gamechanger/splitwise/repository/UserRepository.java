package com.gamechanger.splitwise.repository;

import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gamechanger.splitwise.entity.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public List<UserEntity> findByGroupName(String userGroup);


    @Modifying
    @Transactional
    @Query("DELETE FROM UserEntity u WHERE u.groupName = :groupName")
    void deleteByGroupName(@Param("groupName") String groupName);

//    @Transactional
//    public default void deleteByGroupName(String groupName) {
//        Query query = entityManager.createQuery("DELETE FROM User u WHERE u.groupName = :groupName");
//        query.setParameter("groupName", groupName);
//        query.executeUpdate();
//    }
}
