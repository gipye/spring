package com.example.spring.servicer;

import com.example.spring.model.User;
import com.example.spring.model.RoleType;
import com.example.spring.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserServicer {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Transactional
    public void save(User user) {
        user.setRole(RoleType.USER);

        String rawPassword = user.getPassword();
        user.setPassword(encoder.encode(rawPassword));

        userRepository.save(user);
    }
    @Transactional
    public String userInfoUpdate(User user) {
        User principal = userRepository.findById(user.getId()).orElseThrow(()-> {
            return new IllegalArgumentException("No user id");
        });

        String rawPassword = user.getPassword();
        principal.setPassword(encoder.encode(rawPassword));
        principal.setEmail(user.getEmail());

        return principal.getUsername();
    }
}
