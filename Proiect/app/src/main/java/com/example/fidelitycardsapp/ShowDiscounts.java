package com.example.fidelitycardsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ShowDiscounts extends AppCompatActivity {
    public static final int INSERT_DISCOUNT_CODE = 150;
    private Button addButton, editButton, deleteButton;

    public static final int EDIT_DISCOUNT_REQUEST_CODE = 280;
    private Object object = null;
    private long id = 0;

    private ListView ListOfDiscounts;
    private List<Discount> discounts = new ArrayList<>();

    private DiscountTable discountTable;
    private ManagerDatabase managerDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_discounts);

        managerDatabase = ManagerDatabase.getInstance(getApplicationContext());

        discountTable = new DiscountTable(managerDatabase);
        discountTable.open();
        discounts = discountTable.findAll();
        discountTable.close();

        initComponents();
    }

    private void initComponents() {
        addButton = findViewById(R.id.InserareOferta);
        editButton= findViewById(R.id.EditareOferta);
        deleteButton=findViewById(R.id.StergereOferta);

        ListOfDiscounts = findViewById(R.id.AfisareOferte);

        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), InsertDiscount.class);
            startActivityForResult(intent, INSERT_DISCOUNT_CODE);
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (object != null) {
                    Intent i = new Intent(getApplicationContext(), EditDiscount.class);
                    Discount discount = (Discount) object;
                    id = discount.getId();
                    i.putExtra("discount", discount);
                    startActivityForResult(i, EDIT_DISCOUNT_REQUEST_CODE);
                    discountTable.open();
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (object != null) {
                    discountTable.open();
                    discountTable.delete(((Discount) object).getId());
                    discounts = discountTable.findAll();
                    UpdateListsAfterOperations();
                }
            }
        });

        ListOfDiscounts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                object = ListOfDiscounts.getItemAtPosition(position);
                Log.e(getString(R.string.tagDiscount), object.toString());
            }
        });

        ArrayAdapter<Discount> discountAdaptor = new ArrayAdapter<Discount>(getApplication(),
                android.R.layout.simple_list_item_1,
                discounts);
        ListOfDiscounts.setAdapter(discountAdaptor);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == INSERT_DISCOUNT_CODE
                && resultCode == RESULT_OK && data != null) {
            Discount discountRez = (Discount) data
                    .getSerializableExtra(InsertDiscount.INSERT_DISCOUNT_IdINSERT);
            if (discountRez != null) {
                discountTable.open();
                long id = discountTable.insert(discountRez);
                discountTable.close();
                if (id > 0) {
                    discountRez.setId(id);
                    discounts.add(discountRez);
                    ArrayAdapter adapter=(ArrayAdapter)
                            ListOfDiscounts.getAdapter();
                    Log.i(getString(R.string.tagListDiscounts), adapter.toString());
                    adapter.notifyDataSetChanged();

                }
            }
        }

        else if (requestCode == EDIT_DISCOUNT_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Discount discount = (Discount) data.getSerializableExtra(InsertDiscount.EDIT_DISCOUNT_IdEDIT);

            if (discount != null) {
                discountTable.open();
                discountTable.update(discount, id);
                discounts = discountTable.findAll();
                discountTable.close();
                UpdateListsAfterOperations();
            }
        }
    }

    private void UpdateListsAfterOperations() {
        ArrayAdapter<Discount> objectAdapter = new ArrayAdapter<Discount>(getApplication(), android.R.layout.simple_list_item_1, discounts);
        ListOfDiscounts.setAdapter(objectAdapter);
        ArrayAdapter adapter=(ArrayAdapter)
                ListOfDiscounts.getAdapter();
        adapter.notifyDataSetChanged();
    }

}