package com.example.fidelitycardsapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CardTable implements ConfigDatabase{
    private SQLiteDatabase database;
    private ManagerDatabase managerDatabase;

    public CardTable(ManagerDatabase managerDatabase) {
        this.managerDatabase = managerDatabase;
    }

    public void open() {
        try {
            database = managerDatabase.getWritableDatabase();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void close() {
        try {
            database.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public long insert(Card card) {
        if (card == null) {
            return -1;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(ATRIBUT_CARD_NUMBER, card.getCardNo());
        contentValues.put(ATRIBUT_CARD_EXPIRATION_DATE, DateConverter.fromDate(card.getExpirationDate()));
        contentValues.put(ATRIBUT_CARD_USER, card.getUsername());
        contentValues.put(ATRIBUT_CARD_PARTNER, card.getPartnerName());

        return database.insert(CARD_TABLE_NAME, null, contentValues);
    }

    public boolean delete(Long id) {
        int answer = database.delete(CARD_TABLE_NAME, ATRIBUT_CARD_ID + "=?", new String[]{String.valueOf(id)});
        if (answer == -1) {
            return false;
        } else {
            return true;
        }
    }

    public List<Card> findAll() {
        List<Card> cards = new ArrayList<>();

        Cursor cursor = database
                .query(CARD_TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);

        while (cursor.moveToNext()) {
            Long id = cursor.getLong(cursor.getColumnIndexOrThrow(ATRIBUT_CARD_ID));
            int cardNo = cursor.getInt(cursor.getColumnIndexOrThrow(ATRIBUT_CARD_NUMBER));
            Date expirationDate = DateConverter.fromString(cursor.getString(cursor.getColumnIndexOrThrow(ATRIBUT_CARD_EXPIRATION_DATE)));
            String username = cursor.getString(cursor.getColumnIndexOrThrow(ATRIBUT_CARD_USER));
            String partnerName = cursor.getString(cursor.getColumnIndexOrThrow(ATRIBUT_CARD_PARTNER));

            Card card = new Card(id, cardNo, expirationDate, username, partnerName);
            cards.add(card);
        }

        cursor.close();
        return cards;
    }
}
