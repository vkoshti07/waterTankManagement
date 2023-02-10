package com.example.wtank3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.example.wtank3.model.responseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Athvagate extends AppCompatActivity {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_athvagate);
        getSupportActionBar().hide();
        recyclerView = findViewById(R.id.aRecyclerView);
        swipeRefreshLayout = findViewById(R.id.swipe);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        processData();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                processData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }
    private void processData() {
        Call<List<responseModel>> call = apicontroler
                .getInstance()
                .getapi()
                .getAthvagatData();

        call.enqueue(new Callback<List<responseModel>>() {
            @Override
            public void onResponse(Call<List<responseModel>> call, Response<List<responseModel>> response) {
                List<responseModel> data = response.body();
                adapter a = new adapter(data);
                recyclerView.setAdapter(a);
            }

            @Override
            public void onFailure(Call<List<responseModel>> call, Throwable t) {
                Toast.makeText(Athvagate.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}