package com.example.thoitrang.model;

import androidx.annotation.NonNull;

public class Cart {
    @NonNull
    public final Giay giay;

    public int soLuong;


    public Cart(@NonNull Giay giay) {
        this.giay = giay;
        this.soLuong = 1;
    }

    public Cart(@NonNull Giay giay, int soLuong) {
        this.giay = giay;
        this.soLuong = soLuong;
    }
}
