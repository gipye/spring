package com.example.spring.model;

import lombok.*;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(nullable=false, length=200)
    private String content;
    @ManyToOne
    @JoinColumn(name="boardId")
    private Board board;
    @ManyToOne
    @JoinColumn(name="userId")
    private User user;
}
