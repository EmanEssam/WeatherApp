package com.appswarrior.www.weatherapp.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.appswarrior.www.weatherapp.network.model.WeatherResponse;
import com.appswarrior.www.weatherapp.utils.Constants;
import com.appswarrior.www.weatherapp.utils.PreferencesManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Eman Essam on 14/10/2017.
 */

public class ApiManager {

    public static void getWeatherData(final Context context, double lat, double lon) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiHelper service = retrofit.create(ApiHelper.class);
        Call<WeatherResponse> weatherResponseCall = service.getWeatherData(lat, lon, Constants.METRICS, Constants.API_KEY);

        weatherResponseCall.enqueue(new Callback<WeatherResponse>() {

            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                PreferencesManager preferencesManager = PreferencesManager.getInstance();
                preferencesManager.setPlaceName(response.body().getName());
                preferencesManager.setTemp(response.body().getMain().getTemp() + "");
                preferencesManager.setDesc(response.body().getWeather().get(0).getDescription() + "");

            }


            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }
}
