package com.example.fidelitycardsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class InsertDiscount extends AppCompatActivity {
    public final static String INSERT_DISCOUNT_IdINSERT = "discountInsertion";
    public final static String EDIT_DISCOUNT_IdEDIT = "discountUpdate";

    private Intent intent;
    private Button submit;
    private TextInputEditText tietTitle, tietDescription, tietPercentage, tietCardNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_discount);
        initComponents();
        intent = getIntent();
    }

    private void initComponents() {
        tietTitle = findViewById(R.id.tiet_insertDiscount_title);
        tietDescription = findViewById(R.id.tiet_insertDiscount_description);
        tietPercentage = findViewById(R.id.tiet_insertDiscount_percentage);
        tietCardNo = findViewById(R.id.tiet_insertDiscount_cardNo);

        submit = findViewById(R.id.btn_insertDiscount_addDiscount);

        submit.setOnClickListener(v -> {
            if(isValid()) {
                Discount discount = createDiscount();
                intent.putExtra(INSERT_DISCOUNT_IdINSERT, discount);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private Discount createDiscount() {
        String title = tietTitle.getText().toString();
        String description = tietDescription.getText().toString();
        int percentage = Integer.parseInt(tietPercentage.getText().toString().trim());
        int cardNo = Integer.parseInt(tietCardNo.getText().toString().trim());

        return new Discount(title, description, percentage, cardNo);
    }

    private boolean isValid() {
        if(tietTitle.getText() == null || tietTitle.getText().toString().trim().length()<3){
            Toast.makeText(getApplicationContext(),  R.string.add_title_error_message, Toast.LENGTH_LONG).show();
            return false;
        }
        if(tietDescription.getText() == null || tietDescription.getText().toString().trim().length()<10){
            Toast.makeText(getApplicationContext(),  R.string.add_description_error_message, Toast.LENGTH_LONG).show();
            return false;
        }
        if (tietPercentage.getText() == null || tietPercentage.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), R.string.add_percentage_error_message,
                    Toast.LENGTH_LONG).show();
            return false;
        }
        if (tietCardNo.getText() == null || tietCardNo.getText().toString().trim().isEmpty() ||
                tietCardNo.getText().toString().trim().length() < 6 ||
                tietCardNo.getText().toString().trim().length() > 10){
            Toast.makeText(getApplicationContext(), R.string.add_cardNo_error_message,
                    Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}