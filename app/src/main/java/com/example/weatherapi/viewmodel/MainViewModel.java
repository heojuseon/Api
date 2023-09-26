package com.example.weatherapi.viewmodel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapi.model.Main;
import com.example.weatherapi.model.OpenWeatherClient;
import com.example.weatherapi.model.OpenWeather;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    private final String TAG = "MainViewModel";

    //이 클래스에서는 Model과 통신하여서 날씨 정보를 받아온다.
    private MutableLiveData<OpenWeather> weather = new MutableLiveData<>();
    private OpenWeatherClient client;


    public MutableLiveData<String> weather_result = new MutableLiveData<>("");


    @SuppressLint("DefaultLocale")
    public void init() {

        client = OpenWeatherClient.getInstance();    //OpenWeatherClient 를 통해 객체 받아 온다.
        client.getWeather(new Callback<OpenWeather>() {
            @Override
            public void onResponse(Call<OpenWeather> call, Response<OpenWeather> response) {
                // data.postValue((OpenWeather) response.body());
                OpenWeather result = response.body();
                weather.setValue(result);
                weather_result.setValue(String.format("%.1f", result.getMain().getTemp() - 273.15));
                Log.i(TAG, "Api Connect Success");
                Log.d("MainViewModel", "2. Api Connect Success");
            }

            @Override
            public void onFailure(Call<OpenWeather> call, Throwable t) {
                Log.i(TAG, "OnFailure : " + t.getMessage());
            }
        });  //위에서 받아온 객체로 API 호출 메소드 호출

//        Main main_weather = Objects.requireNonNull(weather.getValue()).getMain();

        // Main main_weather = openWeather.getMain();
        //온도만 구하기
        // weather_result.setValue(String.format("%.1f", main_weather.getTemp() - 273.15));


//        weather.observeForever(openWeather -> {
//            if (openWeather != null && openWeather.getMain() != null) {
//                Main main_weather = openWeather.getMain();
//                //온도만 구하기
//                weather_result.setValue(String.format("%.1f", main_weather.getTemp() - 273.15));
////                weather_result.setValue(main_weather.toString());
//            }
//        });

        Log.i(TAG, "API Connenction finish");
    }

    public LiveData<OpenWeather> getWeather() {


        return weather;
    }

}
