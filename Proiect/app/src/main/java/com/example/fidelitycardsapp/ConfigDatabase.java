package com.example.fidelitycardsapp;

public interface ConfigDatabase {
    String DATABASE="MyFidelityCardsA.db";
    Integer DATABASEVERSION=1;

    //Tabela Cards
    String CARD_TABLE_NAME="Cards";

    String ATRIBUT_CARD_ID="idCard";
    String ATRIBUT_CARD_NUMBER="cardNo";
    String ATRIBUT_CARD_EXPIRATION_DATE="expirationDate";
    String ATRIBUT_CARD_USER ="userName";
    String ATRIBUT_CARD_PARTNER ="partnerName";

    String CREATE_TABLE_CARD = "CREATE TABLE " + CARD_TABLE_NAME + " ( " +
            ATRIBUT_CARD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ATRIBUT_CARD_NUMBER + " INTEGER, " +
            ATRIBUT_CARD_EXPIRATION_DATE + " TEXT, " +
            ATRIBUT_CARD_USER + " TEXT, " +
            ATRIBUT_CARD_PARTNER + " TEXT);";

    String DROP_TABLE_CARD = "DROP TABLE IF EXISTS "+ CARD_TABLE_NAME;

    //Tabela Users
    String USER_TABLE_NAME="Users";

    String ATRIBUT_USER_ID="idUser";
    String ATRIBUT_USER_USER ="userName";
    String ATRIBUT_USER_EMAIL="email";
    String ATRIBUT_USER_PASSWORD ="password";

    String CREATE_TABLE_USER = "CREATE TABLE " + USER_TABLE_NAME + " ( " +
            ATRIBUT_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ATRIBUT_USER_USER + " TEXT, " +
            ATRIBUT_USER_EMAIL + " TEXT, " +
            ATRIBUT_USER_PASSWORD + " TEXT);";

    String DROP_TABLE_USER = "DROP TABLE IF EXISTS "+ USER_TABLE_NAME;

    //Tabela Partners
    String PARTNER_TABLE_NAME="Partners";

    String ATRIBUT_PARTNER_ID="idPartner";
    String ATRIBUT_PARTNER_NAME ="partnerName";
    String ATRIBUT_PARTNER_ADDRESS="address";
    String ATRIBUT_PARTNER_CUI ="cui";

    String CREATE_TABLE_PARTNER = "CREATE TABLE " + PARTNER_TABLE_NAME + " ( " +
            ATRIBUT_PARTNER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ATRIBUT_PARTNER_NAME + " TEXT, " +
            ATRIBUT_PARTNER_ADDRESS + " TEXT, " +
            ATRIBUT_PARTNER_CUI + " TEXT);";

    String DROP_TABLE_PARTNER = "DROP TABLE IF EXISTS "+ PARTNER_TABLE_NAME;

    //Tabela Discounts
    String DISCOUNT_TABLE_NAME="Discounts";

    String ATRIBUT_DISCOUNT_ID="idDiscount";
    String ATRIBUT_DISCOUNT_TITLE ="title";
    String ATRIBUT_DISCOUNT_DESCRIPTION="description";
    String ATRIBUT_DISCOUNT_PERCENTAGE ="percentage";
    String ATRIBUT_DISCOUNT_CARDNO ="cardNo";

    String CREATE_TABLE_DISCOUNT = "CREATE TABLE " + DISCOUNT_TABLE_NAME + " ( " +
            ATRIBUT_DISCOUNT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ATRIBUT_DISCOUNT_TITLE + " TEXT, " +
            ATRIBUT_DISCOUNT_DESCRIPTION + " TEXT, " +
            ATRIBUT_DISCOUNT_PERCENTAGE + " INTEGER, " +
            ATRIBUT_DISCOUNT_CARDNO + " INTEGER);";

    String DROP_TABLE_DISCOUNT = "DROP TABLE IF EXISTS "+ DISCOUNT_TABLE_NAME;

}
