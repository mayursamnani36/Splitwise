package com.gamechanger.splitwise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamechanger.splitwise.entity.User;

@Repository
public interface DepartmentRepository extends JpaRepository<User, Long> {

}
