package com.example.spring.controller;

import com.example.spring.model.Board;
import com.example.spring.servicer.BoardServicer;
import com.example.spring.dto.ResponseDto;
import com.example.spring.config.auth.PrincipalDetail;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@RestController
public class BoardController {
    @Autowired
    BoardServicer boardServicer;
    @PostMapping("/board/write/proc")
    public ResponseDto<String> write(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
        boardServicer.write(board, principal.getUser());
        return new ResponseDto<String>(HttpStatus.OK, "Complete writting");
    }
}
