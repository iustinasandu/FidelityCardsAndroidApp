package com.example.fidelitycardsapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DiscountTable implements ConfigDatabase{
    private SQLiteDatabase database;
    private ManagerDatabase managerDatabase;

    public DiscountTable(ManagerDatabase managerDatabase) {
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

    public long insert(Discount discount) {
        if (discount == null) {
            return -1;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(ATRIBUT_DISCOUNT_TITLE, discount.getTitle());
        contentValues.put(ATRIBUT_DISCOUNT_DESCRIPTION, discount.getDescription());
        contentValues.put(ATRIBUT_DISCOUNT_PERCENTAGE, discount.getPercentage());
        contentValues.put(ATRIBUT_DISCOUNT_CARDNO, discount.getCardNo());

        return database.insert(DISCOUNT_TABLE_NAME, null, contentValues);
    }

    public int update(Discount discount,Long id) {
        ContentValues contentValues=new ContentValues();
        contentValues.put(ATRIBUT_DISCOUNT_TITLE, discount.getTitle());
        contentValues.put(ATRIBUT_DISCOUNT_DESCRIPTION, discount.getDescription());
        contentValues.put(ATRIBUT_DISCOUNT_PERCENTAGE, discount.getPercentage());
        contentValues.put(ATRIBUT_DISCOUNT_CARDNO, discount.getCardNo());

        int i = database.update(DISCOUNT_TABLE_NAME, contentValues, ATRIBUT_DISCOUNT_ID + " = " +  id, null);
        return i;
    }

    public boolean delete(Long id) {
        int answer = database.delete(DISCOUNT_TABLE_NAME, ATRIBUT_DISCOUNT_ID + "=?", new String[]{String.valueOf(id)});
        if (answer == -1) {
            return false;
        } else {
            return true;
        }
    }

    public List<Discount> findAll() {
        List<Discount> discounts = new ArrayList<>();

        Cursor cursor = database
                .query(DISCOUNT_TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);

        while (cursor.moveToNext()) {
            Long id = cursor.getLong(cursor.getColumnIndexOrThrow(ATRIBUT_DISCOUNT_ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(ATRIBUT_DISCOUNT_TITLE));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(ATRIBUT_DISCOUNT_DESCRIPTION));
            int percentage = cursor.getInt(cursor.getColumnIndexOrThrow(ATRIBUT_DISCOUNT_PERCENTAGE));
            int cardNo = cursor.getInt(cursor.getColumnIndexOrThrow(ATRIBUT_DISCOUNT_CARDNO));

            Discount discount = new Discount(id, title, description, percentage, cardNo);
            discounts.add(discount);
        }

        cursor.close();
        return discounts;
    }
}
