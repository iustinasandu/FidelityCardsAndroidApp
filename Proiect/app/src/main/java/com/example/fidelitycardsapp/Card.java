package com.example.fidelitycardsapp;

import java.io.Serializable;
import java.util.Date;

public class Card implements Serializable{
    private Long id;
    private int cardNo;
    private Date expirationDate;
    private String username;
    private String partnerName;


    public Card() {
    }

    public Card(Long id, int cardNo, Date expirationDate, String username, String partnerName) {
        this.id = id;
        this.cardNo = cardNo;
        this.expirationDate = expirationDate;
        this.username = username;
        this.partnerName = partnerName;
    }

    public Card(int cardNo, Date expirationDate, String username, String partnerName) {
        this.cardNo = cardNo;
        this.expirationDate = expirationDate;
        this.username = username;
        this.partnerName = partnerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCardNo() {
        return cardNo;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardNumber=" + cardNo +
                ", expirationDate=" + expirationDate +
                ", username='" + username + '\'' +
                ", partnerName='" + partnerName + '\'' +
                '}';
    }
}
