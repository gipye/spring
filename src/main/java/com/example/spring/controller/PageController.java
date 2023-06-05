package com.example.spring.controller;

import com.example.spring.repository.UserRepository;
import com.example.spring.model.RoleType;
import com.example.spring.model.User;
import com.example.spring.servicer.BoardServicer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {
    @Autowired
    private BoardServicer boardServicer;
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
        System.out.println("id: "+id);
        model.addAttribute("board", boardServicer.detail(id));
        return "board/edit";
    }
}
