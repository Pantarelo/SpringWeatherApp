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
    private Boolean emailNotification;

    @Column(nullable = false)
    private String weatherApiKey;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmailNotification(Boolean emailNotification) {
        this.emailNotification = emailNotification;
    }

    public void setWeatherApiKey(String weatherApiKey) {
        this.weatherApiKey = weatherApiKey;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return this.email;
    }

    public Boolean getEmailNotification() {
        return this.emailNotification;
    }

    public String getWeatherApiKey() {
        return this.weatherApiKey;
    }

    public User getUser() {
        return this.user;
    }
}
