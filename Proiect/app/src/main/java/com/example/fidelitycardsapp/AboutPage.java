package com.example.fidelitycardsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class AboutPage extends AppCompatActivity {
    public final static String ABOUT_PAGE_KEY = String.valueOf(R.string.aboutPageKey);

    private final static String PREF_FILE= String.valueOf(R.string.PrefFileKey);
    private final static String EMAIL= String.valueOf(R.string.emailPreferencesKey);
    private final static String FEEDBACK= String.valueOf(R.string.feedbackPreferencesKey);
    private final static String SWITCH= String.valueOf(R.string.switchPreferencesKEy);
    private final static String RATING_FILE= String.valueOf(R.string.ratingBarFileKey);

    private Intent intent;
    private Button submit;
    private Spinner spnSubject;
    private RatingBar ratingBar;
    private Switch switchSaveData;
    private SharedPreferences sharedPreferences;
    private TextInputEditText tietEmail, tietFeedback;

    private String email, feedback;
    private Boolean switchOnOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_page);
        initComponents();
        intent = getIntent();
    }

    private void initComponents() {
        tietEmail = findViewById(R.id.tiet_aboutPage_email);
        tietFeedback = findViewById(R.id.tiet_aboutPage_feedback);
        submit = findViewById(R.id.btn_aboutPage_submit);
        ratingBar = findViewById(R.id.ratingBar);
        switchSaveData = findViewById(R.id.switchSaveData);

        spnSubject = findViewById(R.id.spn_aboutPage_addSubject);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource(getApplicationContext(),
                        R.array.add_subject_values,
                        android.R.layout.simple_spinner_dropdown_item );
        spnSubject.setAdapter(adapter);


        if (getApplication() != null) {
            sharedPreferences = getApplication()
                    .getSharedPreferences(
                            PREF_FILE,
                            Context.MODE_PRIVATE);
        }
        float value = sharedPreferences.getFloat(RATING_FILE, -1);
        if (value != -1) {
            ratingBar.setRating(value);
        }
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putFloat(RATING_FILE, rating);
                editor.apply();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Feedback feedback = createFeedback();
                saveData();
            }
        });
        loadData();
        updateViews();
    }

    private void saveData() {
        if(switchSaveData.isChecked()) {
            SharedPreferences sharedPreferences = getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(EMAIL, tietEmail.getText().toString());
            editor.putString(FEEDBACK, tietFeedback.getText().toString());
            editor.putBoolean(SWITCH, switchSaveData.isChecked());
            editor.apply();

            Toast.makeText(this, R.string.ToastMessageSaveData, Toast.LENGTH_SHORT).show();
        }
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        email = sharedPreferences.getString(EMAIL, "");
        feedback = sharedPreferences.getString(FEEDBACK, "");
        switchOnOff = sharedPreferences.getBoolean(SWITCH, false);
    }

    private void updateViews(){
        tietEmail.setText(email);
        tietFeedback.setText(feedback);
        switchSaveData.setChecked(switchOnOff);
    }

    private Feedback createFeedback() {
        String email = tietEmail.getText().toString();
        String subject = spnSubject.getSelectedItem().toString();
        String feedback = tietFeedback.getText().toString();

        return new Feedback(email, subject, feedback);
    }

}
