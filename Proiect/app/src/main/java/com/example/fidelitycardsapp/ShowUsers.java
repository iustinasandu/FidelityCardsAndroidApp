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

public class ShowUsers extends AppCompatActivity {
    public static final int INSERT_USER_CODE = 130;
    private Button addButton;

    private Object object = null;

    private ListView ListOfUsers;
    private List<User> users = new ArrayList<>();

    private UserTable userTable;
    private ManagerDatabase managerDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_users);

        managerDatabase = ManagerDatabase.getInstance(getApplicationContext());

        userTable = new UserTable(managerDatabase);
        userTable.open();
        users = userTable.findAll();
        userTable.close();

        initComponents();
    }

    private void initComponents() {
        addButton = findViewById(R.id.InserareUser);

        ListOfUsers = findViewById(R.id.AfisareUseri);

        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), InsertUser.class);
            startActivityForResult(intent, INSERT_USER_CODE);
        });

//        ArrayAdapter<User> userAdaptor = new ArrayAdapter<User>(getApplication(),
//                android.R.layout.simple_list_item_1,
//                users);
        UserAdapter userAdaptor = new UserAdapter(getApplication(), R.layout.lv_users_view, users, getLayoutInflater());
        ListOfUsers.setAdapter(userAdaptor);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == INSERT_USER_CODE
                && resultCode == RESULT_OK && data != null) {
            User userRez = (User) data
                    .getSerializableExtra(InsertUser.INSERT_USER_IdINSERT);
            if (userRez != null) {
                userTable.open();
                long id = userTable.insert(userRez);
                userTable.close();
                if (id > 0) {
                    userRez.setId(id);
                    users.add(userRez);
                    ArrayAdapter adapter=(ArrayAdapter)
                            ListOfUsers.getAdapter();
                    adapter.notifyDataSetChanged();
                }
            }
        }

    }


}