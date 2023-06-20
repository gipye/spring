package com.example.spring.controller;

import com.example.spring.model.Board;
import com.example.spring.model.Reply;
import com.example.spring.servicer.BoardServicer;
import com.example.spring.servicer.ReplyServicer;
import com.example.spring.dto.ResponseDto;
import com.example.spring.config.auth.PrincipalDetail;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class BoardController {
    @Autowired
    BoardServicer boardServicer;
    @Autowired
    ReplyServicer replyServicer;
    @PostMapping("/board/write/proc")
    public ResponseDto<String> write(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
        boardServicer.write(board, principal.getUser());
        return new ResponseDto<String>(HttpStatus.OK, "Complete writting");
    }
    @DeleteMapping("/board/delete/{id}")
    public ResponseDto<String> delete(@PathVariable int id) {
        boardServicer.delete(id);
        return new ResponseDto<String>(HttpStatus.OK, "Complete delete");
    }
    @PutMapping("/board/edit/{id}")
    public ResponseDto<String> edit(@PathVariable int id, @RequestBody Board board) {
        boardServicer.update(id, board);
        return new ResponseDto<String>(HttpStatus.OK, "Complete edit");
    }
    @PostMapping("/board/{boardId}/reply/write")
    public ResponseDto<String> replyWrite(@PathVariable int boardId, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principal) {
        replyServicer.write(principal.getUser().getId(), boardId, reply);
        return new ResponseDto<String>(HttpStatus.OK, "complete reply write");
    }
    @DeleteMapping("/board/{boardId}/reply/{replyId}")
    public ResponseDto<String> replyDelete(@PathVariable int replyId) {
        replyServicer.delete(replyId);
        return new ResponseDto<String>(HttpStatus.OK, "complete reply delete");
    }
}
