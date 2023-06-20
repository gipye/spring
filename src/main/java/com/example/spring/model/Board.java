package com.example.spring.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.Lob;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.OrderBy;
import javax.persistence.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import com.example.spring.model.Reply;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(nullable=false, length=100)
    private String title;
    @Lob
    private String content;
    @ColumnDefault("0")
    private int count;
    @ManyToOne
    @JoinColumn(name="userId")
    private User user;
    @OneToMany(mappedBy="board", fetch=FetchType.EAGER, cascade=CascadeType.REMOVE)
    @JsonIgnoreProperties({"board"})
    @OrderBy("createdate desc")
    private List<Reply> replys;
    @CreationTimestamp
    private Timestamp createDate;
}
