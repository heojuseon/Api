package com.example.weatherapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.example.weatherapi.databinding.ActivityMainBinding;
import com.example.weatherapi.model.OpenWeather;
import com.example.weatherapi.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private OpenWeather opw;

    MainViewModel viewModel;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

         viewModel = new ViewModelProvider(this).get(MainViewModel.class);

         binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
         binding.setLifecycleOwner(this);
         binding.setWeather(viewModel);

        viewModel.init();   //viewModel 의 API 호출 메소드를 호출

        viewModel.getWeather().observe(this, new Observer<OpenWeather>(){

            @Override
            public void onChanged(OpenWeather openWeather) {
                Log.i(TAG,"API Connection finish");
                opw = viewModel.getWeather().getValue();
                Log.i(TAG, opw.toString());
            }
        });



    }
}