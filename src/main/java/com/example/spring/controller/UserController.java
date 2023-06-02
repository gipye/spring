package com.example.spring.controller;

import com.example.spring.servicer.UserServicer;
import com.example.spring.model.RoleType;
import com.example.spring.model.User;
import com.example.spring.dto.ResponseDto;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.function.Supplier;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {
    @Autowired
    UserServicer userServicer;
    @PostMapping("/auth/signup/proc")
    public ResponseDto<String> save(@RequestBody User user) {
        userServicer.save(user);
        return new ResponseDto<String>(HttpStatus.OK, "Success sign up");
    }
    /*
    @PostMapping("/auth/signin/proc")
    public ResponseDto<String> login(@RequestBody User user, HttpSession session) {
        User principal = userRepository.findByUsername(user.getUsername());
        if(principal == null)
            return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR, "Wrong id");

        String encodedPassword = user.getPassword();
        if(user.getPassword().equals(encoder.decode(encodedPassword))) {
            session.setAttribute("principal", principal);
            return new ResponseDto<String>(HttpStatus.OK, "Success login");
        }
        else
            return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR, "Wrong password");
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        if(session.getAttribute("principal") != null)
            session.removeAttribute("principal");
        return "<h1> Logout complete <h1>";
    }
    */
}
