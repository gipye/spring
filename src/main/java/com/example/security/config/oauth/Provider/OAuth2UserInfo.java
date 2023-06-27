package com.example.security.config.oauth.Provider;

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public interface OAuth2UserInfo {
    String getProviderId();
    String getProvider();
    String getEmail();
    String getName();
    static OAuth2UserInfo getOAuth2UserInfo(OAuth2UserRequest userRequest, OAuth2User oauth2User) {
        OAuth2UserInfo oAuth2UserInfo= null;
        if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
            System.out.println("google login request");
            oAuth2UserInfo = new GoogleUserInfo(oauth2User.getAttributes());
        } else if(userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
            System.out.println("naver login request");
            oAuth2UserInfo = new NaverUserInfo((Map)oauth2User.getAttributes().get("response"));
        } else {
            System.out.println("OAuth2 login is not supported:"+userRequest.getClientRegistration().getRegistrationId());
        }

        return oAuth2UserInfo;
    }
}
