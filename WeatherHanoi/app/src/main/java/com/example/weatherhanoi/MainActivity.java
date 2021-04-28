package com.example.weatherhanoi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherhanoi.adapter.NewsAdapter;
import com.example.weatherhanoi.model.Weather;
import com.example.weatherhanoi.network.APIManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvListTemplate;
    TextView tvValue, tvStatus;
    private List<Weather> listData;
    private NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvValue = (TextView) findViewById(R.id.tvValue);
        tvStatus = (TextView) findViewById(R.id.tvStatus);

//        Data Source
        getHour();

        // adapter
        listData = new ArrayList<>();
        adapter = new NewsAdapter(this,listData);

        // layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        // recycleView
        rvListTemplate = findViewById(R.id.rvListTemplate);
        rvListTemplate.setLayoutManager(layoutManager);
        rvListTemplate.setAdapter(adapter);
    }

    private void getHour() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        APIManager service = retrofit.create(APIManager.class);
        service.getHour().enqueue(new Callback<List<Weather>>() {

            @Override
            public void onResponse(Call<List<Weather>> call, Response<List<Weather>> response) {
                if (response.body() == null) return;
                listData = response.body();
                adapter.reloadData(listData);

                tvValue.setText(String.valueOf(listData.get(0).getTemperature().getValue()));
                tvStatus.setText(listData.get(0).getIconPhrase());
            }

            @Override
            public void onFailure(Call<List<Weather>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();
            }
        });
    }
}