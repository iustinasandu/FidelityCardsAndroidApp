package com.example.fidelitycardsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;

public class InsertUser extends AppCompatActivity {
    public final static String INSERT_USER_IdINSERT = "userInsertion";

    private Intent intent;
    private Button submit;
    private TextInputEditText tietUserName, tietEmail,tietPassword;

    public static final int SHOW_USERS_CODE = 180;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_user);
        initComponents();
        intent = getIntent();
    }

    private void initComponents() {
        tietUserName = findViewById(R.id.tiet_insertUser_userName);
        tietEmail = findViewById(R.id.tiet_insertUser_email);
        tietPassword = findViewById(R.id.tiet_insertUser_password);

        submit = findViewById(R.id.btn_insertUser_addUser);

        submit.setOnClickListener(v -> {
            if (isValid()) {
                User user = createUser();
                intent.putExtra(INSERT_USER_IdINSERT, user);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private User createUser() {
        String username = tietUserName.getText().toString();
        String email = tietEmail.getText().toString();
        String password = tietPassword.getText().toString();

        return new User(username, email, password);
    }

    private boolean isValid() {
        if(tietUserName.getText() == null || tietUserName.getText().toString().trim().length()<3){
            Toast.makeText(getApplicationContext(),  R.string.add_userName_error_message, Toast.LENGTH_LONG).show();
            return false;
        }
        if(tietEmail.getText() == null || tietEmail.getText().toString().trim().length()<5 || !tietEmail.getText().toString().contains("@") || !tietEmail.getText().toString().contains(".")){
            Toast.makeText(getApplicationContext(),  R.string.add_email_error_message, Toast.LENGTH_LONG).show();
            return false;
        }
        if (tietPassword.getText() == null || tietPassword.getText().toString().trim().isEmpty() ||
                tietPassword.getText().toString().trim().length() < 4 ||
                tietPassword.getText().toString().trim().length() > 6){
            Toast.makeText(getApplicationContext(), R.string.add_password_error_message,
                    Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }


}