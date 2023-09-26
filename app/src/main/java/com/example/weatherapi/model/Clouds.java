package com.example.weatherapi.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Clouds {
    @SerializedName("all")
    private  float all;

    public float getAll() {
        return all;
    }

    @NonNull
    @Override
    public String toString() {
        return "Clouds{" +
                "all=" + all +
                '}';
    }
}
