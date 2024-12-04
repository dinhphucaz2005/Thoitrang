package com.example.thoitrang.dto;

public class ItemDto {
    private int maGiay;
    private int soLuong;

    public void setMaGiay(int maGiay) {
        this.maGiay = maGiay;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public ItemDto(int maGiay, int soLuong) {
        this.maGiay = maGiay;
        this.soLuong = soLuong;
    }

    public int getMaGiay() {
        return maGiay;
    }

    public int getSoLuong() {
        return soLuong;
    }
}
