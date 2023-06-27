package com.example.security.controller;

import com.example.security.config.auth.PrincipalDetals;
import com.example.security.model.User;
import com.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URI;

@Controller
public class IndexController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/user")
    public @ResponseBody String user() {
        return "user";
    }
    @GetMapping("/admin")
    public @ResponseBody String admin() {
        return "admin";
    }
    @GetMapping("/manager")
    public @ResponseBody String manager() {
        return "manager";
    }
    @GetMapping("/login/form")
    public String login() {
        return "login";
    }
    @GetMapping("/signup/form")
    public String signup() {
        return "signup";
    }
    @PostMapping("/signup/proc")
    public ResponseEntity<?> signupProc(User user) {
        System.out.println(user);
        user.setRole("ROLE_USER");
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/login/form"));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
    @GetMapping("/info")
    public @ResponseBody String info(@AuthenticationPrincipal PrincipalDetals userDetails) {
        System.out.println(userDetails.getUser());
        return "개인정보";
    }
}
