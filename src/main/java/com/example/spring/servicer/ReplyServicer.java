package com.example.spring.servicer;

import com.example.spring.model.Board;
import com.example.spring.model.User;
import com.example.spring.model.Reply;
import com.example.spring.repository.ReplyRepository;
import com.example.spring.repository.UserRepository;
import com.example.spring.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReplyServicer {
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Transactional
    public void write(int userId, int boardId, Reply reply) {
        User user = userRepository.findById(userId).orElseThrow(()->{
            return new IllegalArgumentException("No correponding user id");
        });
        Board board = boardRepository.findById(boardId).orElseThrow(()->{
            return new IllegalArgumentException("No correponding board id");
        });

        reply.setUser(user);
        reply.setBoard(board);
        replyRepository.save(reply);
    }
    @Transactional
    public void delete(int replyId) {
        replyRepository.deleteById(replyId);
    }
}
