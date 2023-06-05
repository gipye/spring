package com.example.spring.servicer;

import com.example.spring.model.Board;
import com.example.spring.model.User;
import com.example.spring.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class BoardServicer {
    @Autowired
    private BoardRepository boardRepository;
    @Transactional
    public void write(Board board, User user) {
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }
    @Transactional(readOnly=true)
    public Page<Board> list(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }
    @Transactional(readOnly=true)
    public Board detail(int id) {
        return boardRepository.findById(id).orElseThrow(()-> {
            return new IllegalArgumentException("No corresponding board id.");
        });
    }
    @Transactional
    public void delete(int id) {
        boardRepository.deleteById(id);
    }
    @Transactional
    public void update(int id, Board board) {
        Board oriBoard = boardRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("No corresponding board id.");
        });

        oriBoard.setTitle(board.getTitle());
        oriBoard.setContent(board.getContent());
    }
}
