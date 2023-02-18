package com.example.fidelitycardsapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Date;
import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {
    private Context context;
    private int resource;
    private List<User> users;
    private LayoutInflater inflater;

    public UserAdapter(@NonNull Context context, int resource, @NonNull List<User> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.users = objects;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        User user = users.get(position);
        if(user == null){
            return view;
        }
        addUserId(view, user.getId());
        addUserName(view, user.getUsername());
        addEmail(view, user.getEmail());
        addUserPassword(view, user.getPassword());
        return view;
    }

    private void addUserId(View view, Long id) {
        TextView textView = view.findViewById(R.id.row_tv_userId);
        populateTextView(textView, String.valueOf(id));
    }

    private void addUserName(View view, String userName) {
        TextView textView = view.findViewById(R.id.row_tv_userName);
        populateTextView(textView, userName);
    }

    private void addEmail(View view, String email) {
        TextView textView = view.findViewById(R.id.row_tv_email);
        populateTextView(textView, email);
    }

    private void addUserPassword(View view, String password) {
        TextView textView = view.findViewById(R.id.row_tv_password);
        populateTextView(textView, password);
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
