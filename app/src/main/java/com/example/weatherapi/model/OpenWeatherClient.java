package com.example.weatherapi.model;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.weatherapi.OpenWeatherAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OpenWeatherClient{
    private final String TAG = "OpenWeatherRepository";
    public static final String WEATHER_URL= "https://api.openweathermap.org/data/2.5/";

    public static Retrofit wretrofit; // 전역변수 문법을 통해 Retrofit 인스턴스를 앱 실행 시 1번만 생성하여 사용 (싱글톤 객체)
    private static OpenWeatherClient instance;
    private OpenWeatherAPI openWeatherAPI;
    private OpenWeather openWeather;

    //OpenWeatherClient 인스턴스 반환
    public static OpenWeatherClient getInstance(){
        if (instance == null){
            instance = new OpenWeatherClient();
        }
        return instance;
    }

    //날씨 정보 받아오기
    public void getWeather(Callback<OpenWeather> callback){

        //retrofit 객체 생성
        wretrofit = new Retrofit.Builder()
                .baseUrl(WEATHER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //인터페이스 객체 생성
        openWeatherAPI = wretrofit.create(OpenWeatherAPI.class);

        //api 와 통신
        callWeatherAPI(callback);
    }

    private void callWeatherAPI(Callback<OpenWeather> callback) {
        //응답을 받아오는 부분
        Call<OpenWeather> call = openWeatherAPI.getWeather("Sangam-dong", "6f38939bc018b8de98482e464f0cb6eb", "kr");
        call.enqueue(callback);
    }


    // 앱이 처음 생성되는 순간, retrofit 인스턴스를 생성
//        wretrofit = new Retrofit.Builder()
//                .baseUrl(WEATHER_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
}
