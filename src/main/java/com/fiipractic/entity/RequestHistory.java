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
    private Boolean q;
    @Column(nullable = false)
    private Boolean api;
    @Column(nullable = false)
    private Integer days;
    @Column(nullable = false)
    private Boolean alerts;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Boolean getQ() {
        return q;
    }

    public void setQ(Boolean q) {
        this.q = q;
    }

    public Boolean getApi() {
        return api;
    }

    public void setApi(Boolean api) {
        this.api = api;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Boolean getAlerts() {
        return alerts;
    }

    public void setAlerts(Boolean alerts) {
        this.alerts = alerts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
