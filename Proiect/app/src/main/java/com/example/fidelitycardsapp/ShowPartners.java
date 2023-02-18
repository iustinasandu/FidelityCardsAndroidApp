package com.example.fidelitycardsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fidelitycardsapp.network.HttpManager;

import java.util.ArrayList;
import java.util.List;

public class ShowPartners extends AppCompatActivity {
    private static final String Partners_URL = "https://jsonkeeper.com/b/DFH2";
    public static final int INSERT_PARTNER_CODE = 140;
    private Button addButton;

    private ListView ListOfPartners;
    private List<Partner> partners = new ArrayList<>();

    private PartnerTable partnerTable;
    private ManagerDatabase managerDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_partners);

        managerDatabase = ManagerDatabase.getInstance(getApplicationContext());

        partnerTable = new PartnerTable(managerDatabase);
        partnerTable.open();
        partners = partnerTable.findAll();
        partnerTable.close();

        initComponents();
        loadPartnersFromURL();
    }

    private void loadPartnersFromURL() {

        Thread thread = new Thread(){
            @Override
            public void run() {
                HttpManager manager = new HttpManager(Partners_URL);
                String result = manager.process();
                new Handler(getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        mainThreadGetPartnersFromHTTPCallback(result);
                    }
                });
            }
        };
        thread.start();
    }

    private void  mainThreadGetPartnersFromHTTPCallback(String result){
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        partners.addAll(PartnerJsonParser.fromJson(result));
        ArrayAdapter adapter=(ArrayAdapter) ListOfPartners.getAdapter();
        adapter.notifyDataSetChanged();
    }

    private void initComponents() {
        addButton = findViewById(R.id.InserarePartener);
        ListOfPartners = findViewById(R.id.AfisareParteneri);

        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), InsertPartner.class);
            startActivityForResult(intent, INSERT_PARTNER_CODE);
        });

//        ArrayAdapter<Partner> partnerAdaptor = new ArrayAdapter<Partner>(getApplication(),
//                android.R.layout.simple_list_item_1,
//                partners);
        PartnerAdapter partnerAdapter = new PartnerAdapter(getApplication(), R.layout.lv_partners_view, partners, getLayoutInflater());
        ListOfPartners.setAdapter(partnerAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == INSERT_PARTNER_CODE
                && resultCode == RESULT_OK && data != null) {
            Partner partnerRez = (Partner) data
                    .getSerializableExtra(InsertPartner.INSERT_PARTNER_IdINSERT);
            if (partnerRez != null) {
                partnerTable.open();
                long id = partnerTable.insert(partnerRez);
                partnerTable.close();
                if (id > 0) {
                    partnerRez.setId(id);
                    partners.add(partnerRez);
                    ArrayAdapter adapter=(ArrayAdapter)
                            ListOfPartners.getAdapter();
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }
}