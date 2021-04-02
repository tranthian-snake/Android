package com.example.weatherhanoi.network;

import com.example.weatherhanoi.model.Weather;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIManager {
    String SERVER_URL="http://dataservice.accuweather.com/";

    @GET("forecasts/v1/hourly/12hour/353412?apikey=QJZE216dGSh4e3BbAT5bOKgZ86x1T1xf&language=vi-vn&metric=true")
    Call<Weather> getHour();

    @GET("forecasts/v1/daily/5day/353412?apikey=QJZE216dGSh4e3BbAT5bOKgZ86x1T1xf&language=vi-vn&metric=true")
    Call<Weather> getDay();
}
