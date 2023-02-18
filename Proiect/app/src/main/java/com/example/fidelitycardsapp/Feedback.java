package com.example.fidelitycardsapp;

import java.io.Serializable;

public class Feedback implements Serializable {
    private Long id;
    private String email;
    private String subject;
    private String feedback;

    public Feedback() {
    }

    public Feedback(Long id, String email, String subject, String feedback) {
        this.id = id;
        this.email = email;
        this.subject = subject;
        this.feedback = feedback;
    }

    public Feedback(String email, String subject, String feedback) {
        this.email = email;
        this.subject = subject;
        this.feedback = feedback;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", subject='" + subject + '\'' +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
