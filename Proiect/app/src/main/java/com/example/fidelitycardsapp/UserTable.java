package com.example.fidelitycardsapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserTable implements ConfigDatabase{
    private SQLiteDatabase database;
    private ManagerDatabase managerDatabase;

    public UserTable(ManagerDatabase managerDatabase) {
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

    public long insert(User user) {
        if (user == null) {
            return -1;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(ATRIBUT_USER_USER, user.getUsername());
        contentValues.put(ATRIBUT_USER_EMAIL, user.getEmail());
        contentValues.put(ATRIBUT_USER_PASSWORD, user.getPassword());

        return database.insert(USER_TABLE_NAME, null, contentValues);
    }

    public int update(User user,Long id) {
        ContentValues contentValues=new ContentValues();
        contentValues.put(ATRIBUT_USER_USER, user.getUsername());
        contentValues.put(ATRIBUT_USER_EMAIL, user.getEmail());
        contentValues.put(ATRIBUT_USER_PASSWORD, user.getPassword());
        int i = database.update(USER_TABLE_NAME, contentValues, ATRIBUT_USER_ID + " = " +  id, null);
        return i;
    }

    public boolean delete(Long id) {
        int answer = database.delete(USER_TABLE_NAME, ATRIBUT_USER_ID + "=?", new String[]{String.valueOf(id)});
        if (answer == -1) {
            return false;
        } else {
            return true;
        }
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        Cursor cursor = database
                .query(USER_TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);

        while (cursor.moveToNext()) {
            Long id = cursor.getLong(cursor.getColumnIndexOrThrow(ATRIBUT_USER_ID));
            String username = cursor.getString(cursor.getColumnIndexOrThrow(ATRIBUT_USER_USER));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(ATRIBUT_USER_EMAIL));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(ATRIBUT_USER_PASSWORD));

            User user = new User(id, username, email, password);
            users.add(user);
        }

        cursor.close();
        return users;
    }
}
