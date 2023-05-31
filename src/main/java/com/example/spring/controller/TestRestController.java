package com.example.spring.controller;

import com.example.spring.repository.UserRepository;
import com.example.spring.model.RoleType;
import com.example.spring.model.User;
import com.example.spring.dto.ResponseDto;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class TestRestController {
    @Autowired
    UserRepository userRepository;
    @PostMapping("/signup/proc")
    public ResponseDto<String> save(@RequestBody User user) {
        user.setRole(RoleType.USER);

        userRepository.save(user);
        return new ResponseDto<String>(HttpStatus.OK, "Success sign up");
    }
}
