package com.example.wtank3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.wtank3.model.homeModel;

import java.util.ArrayList;

public class home extends AppCompatActivity {
    RecyclerView recyclerView;
    Button logout;

    ArrayList<homeModel> list = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().hide();
        logout = findViewById(R.id.Logout);
        recyclerView= findViewById(R.id.homeRecycler);
        list.add(new homeModel("Katargam Zone"));
        list.add(new homeModel("Majuragate Zone"));
        list.add(new homeModel("Athvagate Zone"));
        list.add(new homeModel("Office Zone"));

        homeAdapter homeAdapter = new homeAdapter(this,list);
        recyclerView.setAdapter(homeAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("save",MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.clear();
                editor.apply();

                Intent intent=new Intent(home.this,login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}