package com.example.fidelitycardsapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PartnerTable implements ConfigDatabase{
    private SQLiteDatabase database;
    private ManagerDatabase managerDatabase;

    public PartnerTable(ManagerDatabase managerDatabase) {
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

    public long insert(Partner partner) {
        if (partner == null) {
            return -1;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(ATRIBUT_PARTNER_NAME, partner.getPartnerName());
        contentValues.put(ATRIBUT_PARTNER_ADDRESS, partner.getAddress());
        contentValues.put(ATRIBUT_PARTNER_CUI, partner.getCUI());

        return database.insert(PARTNER_TABLE_NAME, null, contentValues);
    }

    public boolean delete(Long id) {
        int answer = database.delete(PARTNER_TABLE_NAME, ATRIBUT_PARTNER_ID + "=?", new String[]{String.valueOf(id)});
        if (answer == -1) {
            return false;
        } else {
            return true;
        }
    }

    public List<Partner> findAll() {
        List<Partner> partners = new ArrayList<>();

        Cursor cursor = database
                .query(PARTNER_TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);

        while (cursor.moveToNext()) {
            Long id = cursor.getLong(cursor.getColumnIndexOrThrow(ATRIBUT_PARTNER_ID));
            String partnerName = cursor.getString(cursor.getColumnIndexOrThrow(ATRIBUT_PARTNER_NAME));
            String address = cursor.getString(cursor.getColumnIndexOrThrow(ATRIBUT_PARTNER_ADDRESS));
            String cui = cursor.getString(cursor.getColumnIndexOrThrow(ATRIBUT_PARTNER_CUI));

            Partner partner = new Partner(id, partnerName, address, cui);
            partners.add(partner);
        }

        cursor.close();
        return partners;
    }
}
