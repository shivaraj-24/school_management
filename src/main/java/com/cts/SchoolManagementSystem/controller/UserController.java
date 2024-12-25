package com.cts.SchoolManagementSystem.controller;

import com.cts.SchoolManagementSystem.entity.User;
import com.cts.SchoolManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody User user){
        service.add(user);
        return new ResponseEntity<>("User is created Successfully", HttpStatus.OK);
    }
}
