package com.fiipractic.entity;

import jakarta.persistence.*;

@Entity
public class RequestHistory {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String lat;
    @Column(nullable = false)
    private String lon;
    @Column(nullable = false)
    private String response;
    @Column(nullable = false)
    private boolean q;
    @Column(nullable = false)
    private boolean api;
    @Column(nullable = false)
    private Integer days;
    @Column(nullable = false)
    private Boolean alerts;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
