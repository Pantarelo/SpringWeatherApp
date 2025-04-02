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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmailNotification(boolean emailNotification) {
        this.emailNotification = emailNotification;
    }

    public void setWeatherApiKey(String weatherApiKey) {
        this.weatherApiKey = weatherApiKey;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public boolean getEmailNotification() {
        return emailNotification;
    }

    public String getWeatherApiKey() {
        return weatherApiKey;
    }

    public User getUser() {
        return user;
    }
}
