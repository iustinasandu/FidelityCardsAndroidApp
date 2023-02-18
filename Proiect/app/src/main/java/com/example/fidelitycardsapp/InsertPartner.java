package com.example.fidelitycardsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;

public class InsertPartner extends AppCompatActivity {
    public final static String INSERT_PARTNER_IdINSERT = "partnerInsertion";
    private Intent intent;
    private Button submit;
    private TextInputEditText tietPartnerName, tietAddress, tietCui;

    public static final int SHOW_PARTNERS_CODE = 190;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_partner);
        initComponents();
        intent = getIntent();
    }

    private void initComponents() {
        tietPartnerName = findViewById(R.id.tiet_insertPartner_partnerName);
        tietAddress = findViewById(R.id.tiet_insertPartner_address);
        tietCui = findViewById(R.id.tiet_insertPartner_cui);

        submit = findViewById(R.id.btn_insertPartner_addPartner);

        submit.setOnClickListener(v -> {
            if(isValid()) {
                Partner partner = createPartner();
                intent.putExtra(INSERT_PARTNER_IdINSERT, partner);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private Partner createPartner() {
        String partnerName = tietPartnerName.getText().toString();
        String address = tietAddress.getText().toString();
        String cui = tietCui.getText().toString();

        return new Partner(partnerName, address, cui);
    }

    private boolean isValid() {
        if(tietPartnerName.getText() == null || tietPartnerName.getText().toString().trim().length()<3){
            Toast.makeText(getApplicationContext(),  R.string.add_partnerName_error_message, Toast.LENGTH_LONG).show();
            return false;
        }
        if(tietAddress.getText() == null || tietAddress.getText().toString().trim().length()<10){
            Toast.makeText(getApplicationContext(),  R.string.add_address_error_message, Toast.LENGTH_LONG).show();
            return false;
        }
        if(tietCui.getText() == null || tietCui.getText().toString().trim().length()<1 || tietCui.getText().toString().trim().length()>11){
            Toast.makeText(getApplicationContext(),  R.string.add_cui_error_message, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}