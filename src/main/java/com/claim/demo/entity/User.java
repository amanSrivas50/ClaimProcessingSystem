package com.claim.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column
    private String username;

    @Column
    private String passwordHash;

    @Column
    private String email;

    @Column
    private String role;

    @Column
    private Date createdAt;

    @Column
    private Date updatedAt;

    @Column
    private Date lastLogin;

    @Column
    private String status;


}
