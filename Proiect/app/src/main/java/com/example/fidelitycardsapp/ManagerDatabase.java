package com.example.fidelitycardsapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ManagerDatabase extends SQLiteOpenHelper implements ConfigDatabase {

    private static ManagerDatabase controller;

    public static ManagerDatabase getInstance(Context context) {
        if (controller == null) {
            synchronized (ManagerDatabase.class) {
                if (controller == null) {
                    controller = new ManagerDatabase(context);
                }
            }
        }
        return controller;
    }

    private ManagerDatabase(Context context) {
        super(context, DATABASE, null, DATABASEVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE_CARD);
            db.execSQL(CREATE_TABLE_USER);
            db.execSQL(CREATE_TABLE_PARTNER);
            db.execSQL(CREATE_TABLE_DISCOUNT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(DROP_TABLE_CARD);
            db.execSQL(DROP_TABLE_USER);
            db.execSQL(DROP_TABLE_PARTNER);
            db.execSQL(DROP_TABLE_DISCOUNT);
            onCreate(db);
        } catch (Exception e) {
        }
    }


}

