package com.example.fidelitycardsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fidelitycardsapp.R;


import java.util.List;

public class PartnerAdapter extends ArrayAdapter<Partner> {
    private Context context;
    private int resource;
    private List<Partner> partners;
    private LayoutInflater inflater;

    public PartnerAdapter(@NonNull Context context, int resource, @NonNull List<Partner> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.partners = objects;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        Partner partner = partners.get(position);
        if(partner == null){
            return view;
        }
        addPartnerId(view, partner.getId());
        addPartnerName(view, partner.getPartnerName());
        addAddress(view, partner.getAddress());
        addCUI(view, partner.getCUI());
        return view;
    }

    private void addPartnerId(View view, Long id) {
        TextView textView = view.findViewById(R.id.row_tv_partnerId);
        populateTextView(textView, String.valueOf(id));
    }

    private void addPartnerName(View view, String partnerName) {
        TextView textView = view.findViewById(R.id.row_tv_partnerName);
        populateTextView(textView, partnerName);
    }

    private void addAddress(View view, String address) {
        TextView textView = view.findViewById(R.id.row_tv_address);
        populateTextView(textView, address);
    }

    private void addCUI(View view, String CUI) {
        TextView textView = view.findViewById(R.id.row_tv_cui);
        populateTextView(textView, CUI);
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
