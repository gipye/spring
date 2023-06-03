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
    public Page<Board> boardList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }
}
