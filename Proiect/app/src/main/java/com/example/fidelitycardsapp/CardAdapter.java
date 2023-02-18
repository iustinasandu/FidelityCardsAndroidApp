package com.example.fidelitycardsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Date;
import java.util.List;

public class CardAdapter extends ArrayAdapter<Card> {
    private Context context;
    private int resource;
    private List<Card> cards;
    private LayoutInflater inflater;

    public CardAdapter(@NonNull Context context, int resource, @NonNull List<Card> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.cards = objects;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        Card card = cards.get(position);
        if(card == null){
            return view;
        }
        addCardId(view, card.getId());
        addCardNo(view, card.getCardNo());
        addCardExpirationDate(view, card.getExpirationDate());
        addCardUserName(view, card.getUsername());
        addCardPartnerName(view, card.getPartnerName());
        return view;
    }

    private void addCardId(View view, Long id) {
        TextView textView = view.findViewById(R.id.row_tv_cardId);
        populateTextView(textView, String.valueOf(id));
    }

    private void addCardNo(View view, int cardNo) {
        TextView textView = view.findViewById(R.id.row_tv_cardNo);
        populateTextView(textView, String.valueOf(cardNo));
    }

    private void addCardExpirationDate(View view, Date expirationDate) {
        TextView textView = view.findViewById(R.id.row_tv_expirationDate);
        populateTextView(textView, DateConverter.fromDate(expirationDate));
    }

    private void addCardUserName(View view, String userName) {
        TextView textView = view.findViewById(R.id.row_tv_cardUserName);
        populateTextView(textView, userName);
    }

    private void addCardPartnerName(View view, String partnerName) {
        TextView textView = view.findViewById(R.id.row_tv_cardPartnerName);
        populateTextView(textView, partnerName);
    }


    private void populateTextView(TextView textView, String value){
        if(value!=null && !value.trim().isEmpty()){
            textView.setText(value);
        }
        else{
            textView.setText(R.string.card_adapter_default_value);
        }
    }

}
