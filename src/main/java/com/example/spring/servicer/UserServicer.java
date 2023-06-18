package com.example.spring.servicer;

import com.example.spring.model.User;
import com.example.spring.model.RoleType;
import com.example.spring.model.KakaoProfile;
import com.example.spring.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserServicer {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Value("${kakao.key}")
    private String kakao_login_password;
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

        if(principal.getOauth() != null)
            return null;

        String rawPassword = user.getPassword();
        principal.setPassword(encoder.encode(rawPassword));
        principal.setEmail(user.getEmail());

        return principal.getUsername();
    }
    @Transactional
    public User kakaoLogin(KakaoProfile kakaoProfile) {
        String email = kakaoProfile.getKakao_account().getEmail();
        String username = kakaoProfile.getId().toString()+"_I_am_kakao_user!!_"+email;
        User user = userRepository.findByUsername(username).orElseGet(()-> {
            return new User();
        });

        if(user.getUsername() == null) {
            System.out.println("... auto signup ...");

            user = User.builder().username(username).password(kakao_login_password).email(email).oauth("kakao").build();
            this.save(user);

            System.out.println("Complete auto signup");
        }

        return user;
    }
}
