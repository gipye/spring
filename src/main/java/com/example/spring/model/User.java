package com.example.spring.model;

import com.example.spring.RoleType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;

import java.sql.Timestamp;
import org.hibernate.annotations.CreationTimestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(nullable=false, length=30, unique=true)
    private String username;
    @Column(nullable=false, length=100, unique=false)
    private String password;
    @Column(nullable=false, length=50, unique=true)
    private String email;
    @Enumerated(EnumType.STRING)
    private RoleType role;
    @CreationTimestamp
    private Timestamp createDate;
}
