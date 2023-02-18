package com.example.fidelitycardsapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import java.util.Date;

public class InsertCard extends AppCompatActivity {
    public final static String INSERT_CARD_IdINSERT = "cardInsertion";
    private Intent intent;
    private Button submit;
    private TextInputEditText tietCardNo, tietExpirationDate,tietPartnerName, tietUserName;

    public static final int SHOW_CARDS_CODE = 170;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_card);
        initComponents();
        intent = getIntent();
    }

    private void initComponents() {
        tietCardNo = findViewById(R.id.tiet_insertCard_cardNumber);
        tietExpirationDate = findViewById(R.id.tiet_insertCard_expirationDate);
        tietPartnerName = findViewById(R.id.tiet_insertCard_partnerName);
        tietUserName = findViewById(R.id.tiet_insertCard_userName);

        submit = findViewById(R.id.btn_insertCard_addCard);

        submit.setOnClickListener(v -> {
            if(isValid()) {
                Card card = createCard();
                intent.putExtra(INSERT_CARD_IdINSERT, card);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private Card createCard() {
        int cardNo = Integer.parseInt(tietCardNo.getText().toString().trim());
        Date expirationDate = DateConverter.fromString(tietExpirationDate.getText().toString());
        String partnerName = tietPartnerName.getText().toString();
        String userName = tietUserName.getText().toString();

        return new Card(cardNo, expirationDate, userName, partnerName);
    }

    private boolean isValid() {
        if (tietCardNo.getText() == null || tietCardNo.getText().toString().trim().isEmpty() ||
                tietCardNo.getText().toString().trim().length() < 6 ||
                tietCardNo.getText().toString().trim().length() > 10){
            Toast.makeText(getApplicationContext(), R.string.add_cardNo_error_message,
                    Toast.LENGTH_LONG).show();
            return false;
        }
        if(tietExpirationDate.getText() == null
                || DateConverter.fromString(tietExpirationDate.getText().toString()) == null){
            Toast.makeText(getApplicationContext(),
                    R.string.add_expirationDate_error_message,
                    Toast.LENGTH_LONG).show();
            return false;
        }
        if(tietPartnerName.getText() == null || tietPartnerName.getText().toString().trim().length()<3){
            Toast.makeText(getApplicationContext(),  R.string.add_partnerName_error_message, Toast.LENGTH_LONG).show();
            return false;
        }
        if(tietUserName.getText() == null || tietUserName.getText().toString().trim().length()<3){
            Toast.makeText(getApplicationContext(),  R.string.add_userName_error_message, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}