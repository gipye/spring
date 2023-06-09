package com.example.spring.model;

import lombok.Data;

@Data
public class KakaoProfile {
    @Data
    public class KakaoAccount {
        @Data
        public class Profile {
            public String nickname;
        }
        
        public Boolean profile_nickname_needs_agreement;
        public Profile profile;
        public Boolean has_email;
        public Boolean email_needs_agreement;
        public Boolean is_email_valid;
        public Boolean is_email_verified;
        public String email;
    }
    @Data
    public class Properties {
        public String nickname;
    }

    public Long id;
    public String connected_at;
    public Properties properties;
    public KakaoAccount kakao_account;
}
