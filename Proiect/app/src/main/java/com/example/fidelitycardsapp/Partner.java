package com.example.fidelitycardsapp;

import java.io.Serializable;

public class Partner implements Serializable{
    private Long id;
    private String partnerName;
    private String address;
    private String CUI;

    public Partner() {
    }

    public Partner(Long id, String partnerName, String address, String CUI) {
        this.id = id;
        this.partnerName = partnerName;
        this.address = address;
        this.CUI = CUI;
    }

    public Partner(String partnerName, String address, String CUI) {
        this.partnerName = partnerName;
        this.address = address;
        this.CUI = CUI;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCUI() {
        return CUI;
    }

    public void setCUI(String CUI) {
        this.CUI = CUI;
    }

    @Override
    public String toString() {
        return "Partner{" +
                "id=" + id +
                ", partnerName='" + partnerName + '\'' +
                ", address='" + address + '\'' +
                ", CUI='" + CUI + '\'' +
                '}';
    }
}
