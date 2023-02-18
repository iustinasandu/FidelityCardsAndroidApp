package com.example.fidelitycardsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class ShowCards extends AppCompatActivity {
    public static final int INSERT_CARD_CODE = 120;
    private Button addButton;

    private ListView ListOfCards;
    private List<Card> cards = new ArrayList<>();

    private CardTable cardTable;
    private ManagerDatabase managerDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_cards);

        managerDatabase = ManagerDatabase.getInstance(getApplicationContext());

        cardTable = new CardTable(managerDatabase);
        cardTable.open();
        cards = cardTable.findAll();
        cardTable.close();

        initComponents();
    }

    private void initComponents() {
        addButton = findViewById(R.id.InserareCard);
        ListOfCards = findViewById(R.id.AfisareCarduri);

        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), InsertCard.class);
            startActivityForResult(intent, INSERT_CARD_CODE);
        });

//        ArrayAdapter<Card> cardAdaptor = new ArrayAdapter<Card>(getApplication(),
//                android.R.layout.simple_list_item_1,
//                cards);
        CardAdapter cardAdapter = new CardAdapter(getApplication(), R.layout.lv_cards_view, cards, getLayoutInflater());
        ListOfCards.setAdapter(cardAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == INSERT_CARD_CODE
                && resultCode == RESULT_OK && data != null) {
            Card cardRez = (Card) data
                    .getSerializableExtra(InsertCard.INSERT_CARD_IdINSERT);
            if (cardRez != null) {
                cardTable.open();
                long id = cardTable.insert(cardRez);
                cardTable.close();
                if (id > 0) {
                    cardRez.setId(id);
                    cards.add(cardRez);
                    ArrayAdapter adapter=(ArrayAdapter)
                            ListOfCards.getAdapter();
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }

}