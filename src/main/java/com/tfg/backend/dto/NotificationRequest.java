package com.tfg.backend.dto;

public class NotificationRequest {

    private String title;
    private String body;

    public NotificationRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}