package com.example.spring.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.spring.model.User;

@Service    // assign Bean
public class PrincipalDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User principal = userRepository.findByUsername(username).orElseThrow(()->{
            return new UsernameNotFoundException("Username is not exist");
        });

        return new PrincipalDetail(principal);
    }
}
