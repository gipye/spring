package com.example.spring.controller;

import com.example.spring.repository.UserRepository;
import com.example.spring.model.RoleType;
import com.example.spring.model.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class PageController {
    @GetMapping("/auth/signup")
    public String signup() {
        return "signup";
    }
    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/auth/signin")
    public String login() {
        return "login";
    }
    @GetMapping("/board/write")
    public String savePage() {
        return "/board/write";
    }
}
