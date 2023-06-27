package com.example.security.config.oauth.Provider;

import java.util.Map;

public class NaverUserInfo implements OAuth2UserInfo {
    private Map<String, String> attributes;
    public NaverUserInfo(Map<String, String> attributes) {
        this.attributes = attributes;
    }
    @Override
    public String getProviderId() {
        return attributes.get("id");
    }
    @Override
    public String getProvider() {
        return "naver";
    }
    @Override
    public String getEmail() {
        return attributes.get("email");
    }
    @Override
    public String getName() {
        return attributes.get("name");
    }
}
