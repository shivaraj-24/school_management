package com.cts.SchoolManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.SchoolManagementSystem.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByUsername(String username);


}