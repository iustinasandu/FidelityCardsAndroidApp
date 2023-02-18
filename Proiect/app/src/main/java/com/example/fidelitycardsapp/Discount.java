package com.example.fidelitycardsapp;

import java.io.Serializable;

public class Discount implements Serializable{
    private Long id;
    private String title;
    private String description;
    private int percentage;
    private int cardNo;

    public Discount() {
    }

    public Discount(Long id, String title, String description, int percentage, int cardNo) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.percentage = percentage;
        this.cardNo = cardNo;
    }

    public Discount(String title, String description, int percentage, int cardNo) {
        this.title = title;
        this.description = description;
        this.percentage = percentage;
        this.cardNo = cardNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public int getCardNo() {
        return cardNo;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", percentage=" + percentage +
                ", cardNo=" + cardNo +
                '}';
    }
}
