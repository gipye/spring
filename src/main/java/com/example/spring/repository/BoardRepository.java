package com.example.spring.repository;

import com.example.spring.model.Board;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
}
