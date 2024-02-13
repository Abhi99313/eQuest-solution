package com.example.mvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    private UserAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        userAdapter = new UserAdapter(new ArrayList<>());
        recyclerView.setAdapter(userAdapter);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userViewModel.getUsers().observe(this, users -> userAdapter.updateData(users));

        userViewModel.fetchUsers();
    }

    public void showDetails(View view) {

    }
}