package com.fiipractic.entity;

import jakarta.persistence.*;

@Entity
public class UserProfile {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private boolean emailNotification;

    @Column(nullable = false)
    private String weatherApiKey;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;
}
