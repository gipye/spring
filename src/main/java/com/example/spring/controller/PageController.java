package com.example.spring.controller;

import com.example.spring.repository.UserRepository;
import com.example.spring.model.RoleType;
import com.example.spring.model.User;
import com.example.spring.model.OAuthToken;
import com.example.spring.model.KakaoProfile;
import com.example.spring.servicer.BoardServicer;
import com.example.spring.servicer.UserServicer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.client.RestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpHeaders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class PageController {
    @Autowired
    private UserServicer userServicer;
    @Autowired
    private BoardServicer boardServicer;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Value("${kakao.key}")
    private String kakao_login_password;
    @GetMapping("/auth/signup")
    public String signup() {
        return "signup";
    }
    @GetMapping("/")
    public String index(Model model, @PageableDefault(size=2, sort="createDate", direction=Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("boards", boardServicer.list(pageable));
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
    @GetMapping("/board/{id}")
    public String boardDetail(Model model, @PathVariable int id) {
        model.addAttribute("board", boardServicer.detail(id));
        return "board/detail";
    }
    @GetMapping("/board/edit/{id}")
    public String updatePage(Model model, @PathVariable int id) {
        model.addAttribute("board", boardServicer.detail(id));
        return "board/edit";
    }
    @GetMapping("/myinfo")
    public String userInfo() {
        return "myinfo";
    }
    @GetMapping("/auth/kakao/callback")
    public @ResponseBody String kakaoCallbackPage(String code) {

        // 1. Get Access Token

		// Use RestTemplate libary
        RestTemplate rt = new RestTemplate();

        // Set http header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // Set http body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "e85dcad6dd916df7f3a2133f57a72663");
        params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
        params.add("code", code);

        // Combine header and body
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        // Request and get response
        // response obtain kakao server's response
        ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST, kakaoTokenRequest, String.class);

        // Objet mapping
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oauthToken = null;
        try {
            oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        } catch(JsonMappingException e) {
            e.printStackTrace();
        } catch(JsonProcessingException e) {
            e.printStackTrace();
        }
        //System.out.println(oauthToken.getAccess_token());



        // 2. Get User Information

		// Use RestTemplate libary
        rt = new RestTemplate();

        // Set http header
        headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        headers.add("Authorization", "Bearer "+oauthToken.getAccess_token());

        // Create Http Request instance
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);

        // Request and get response
        // response obtain kakao server's response
        response = rt.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST, kakaoProfileRequest, String.class);

        // Object mapping
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper.readValue(response.getBody(), KakaoProfile.class);
        } catch(JsonMappingException e) {
            e.printStackTrace();
        } catch(JsonProcessingException e) {
            e.printStackTrace();
        }
        //System.out.println("id: "+kakaoProfile.getId());
        //System.out.println("email: "+kakaoProfile.getKakao_account().getEmail());

        User kakaoUser = userServicer.kakaoLogin(kakaoProfile);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), kakao_login_password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("login complete");

        return "redirect:/";
    }
}
