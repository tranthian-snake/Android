package com.example.weatherhanoi.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.weatherhanoi.R;
import com.example.weatherhanoi.model.Weather;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<Weather> list;

    public NewsAdapter(Activity activity, List<Weather> list) {
        this.activity = activity;
        this.list=list;
    }

    public void reloadData(List<Weather> list){
        this.list = list;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=activity.getLayoutInflater();
        View itemView =activity.getLayoutInflater().inflate(R.layout.activity_list_temperature, parent, false);
        NewsHolder holder=new NewsHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NewsHolder hd=(NewsHolder) holder;
        Weather model=list.get(position);
        hd.tvHour.setText(convertTime(model.getDateTime()));
        hd.tvValue.setText(model.getTemperature().getValue()+"");
        String url="";
        if(model.getWeatherIcon()<10){
            url="https://developer.accuweather.com/sites/default/files/0"+model.getWeatherIcon()+"-s.png";
        }else {
            url="https://developer.accuweather.com/sites/default/files/"+model.getWeatherIcon()+"-s.png";
        }
        Glide.with(activity).load(url).into(hd.ivCover);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class NewsHolder extends RecyclerView.ViewHolder{
        TextView tvHour, tvValue;
        ImageView ivCover;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            tvHour= itemView.findViewById(R.id.tvHour);
            tvValue= itemView.findViewById(R.id.tvValue);
            ivCover= itemView.findViewById(R.id.ivCover);
        }
    }

    public String convertTime(String inputTime) {
        SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = inFormat.parse(inputTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat outFormat = new SimpleDateFormat("ha");
        String goal = outFormat.format(date);
        return goal;
    }
}
