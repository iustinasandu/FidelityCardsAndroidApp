package com.example.fidelitycardsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

//    public static final int INSERT_CARD_CODE = 120;
//    public static final int INSERT_USER_CODE = 130;
//    public static final int INSERT_PARTNER_CODE = 140;
//    public static final int INSERT_DISCOUNT_CODE = 150;
    public static final int ABOUT_PAGE_CODE = 160;
    public static final int SHOW_CARDS_CODE = 170;
    public static final int SHOW_USERS_CODE = 180;
    public static final int SHOW_PARTNERS_CODE = 190;
    public static final int SHOW_DISCOUNTS_CODE = 250;

    private Button uploadImage;
    private ImageView imageView;
    int SELECT_IMAGE_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uploadImage = findViewById(R.id.btn_aboutPage_uploadImage);
        imageView = findViewById(R.id.picture_feedback);

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType(getString(R.string.imageType));
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, getString(R.string.intentTitle)), SELECT_IMAGE_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            Uri uri = data.getData();
            imageView.setImageURI(uri);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();

        if(id == R.id.aboutPage){
            Intent intent = new Intent(getApplicationContext(), AboutPage.class);
            startActivityForResult(intent, ABOUT_PAGE_CODE);
        }
        if(id == R.id.showCards){
            Intent intent = new Intent(getApplicationContext(), ShowCards.class);
            startActivityForResult(intent, SHOW_CARDS_CODE);
        }
        if(id == R.id.showUsers){
            Intent intent = new Intent(getApplicationContext(), ShowUsers.class);
            startActivityForResult(intent, SHOW_USERS_CODE);
        }
        if(id == R.id.showPartners){
            Intent intent = new Intent(getApplicationContext(), ShowPartners.class);
            startActivityForResult(intent, SHOW_PARTNERS_CODE);
        }
        if(id == R.id.showDiscounts){
            Intent intent = new Intent(getApplicationContext(), ShowDiscounts.class);
            startActivityForResult(intent, SHOW_DISCOUNTS_CODE);
        }
        return true;
    }
}