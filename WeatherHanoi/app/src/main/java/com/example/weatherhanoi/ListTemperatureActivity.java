package com.example.weatherhanoi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

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

public class ListTemperatureActivity extends AppCompatActivity {

    RecyclerView rvListTemplate;
    TextView tvValue, tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_temperature);

        tvValue = (TextView) findViewById(R.id.tvValue);
        tvStatus = (TextView) findViewById(R.id.tvStatus);

//        Data Source
        getHour();

//        Layout Manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

//        RecyclerView
        rvListTemplate = (RecyclerView) findViewById(R.id.rvListTemplate);
        rvListTemplate.setLayoutManager(layoutManager);
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
                List<Weather> list = response.body();
                NewsAdapter adapter = new NewsAdapter(ListTemperatureActivity.this, list);
                rvListTemplate.setAdapter(adapter);
                Weather weather = list.get(0);
                tvValue.setText(weather.getTemperature().getValue().intValue() + "°");
                tvStatus.setText(weather.getIconPhrase());
            }

            @Override
            public void onFailure(Call<List<Weather>> call, Throwable t) {

            }
        });
    }
}