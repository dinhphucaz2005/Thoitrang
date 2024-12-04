package com.example.thoitrang.model;

@Deprecated(since = "This class is deprecated")
public class ExHoaDon {
    public int maHD;
    public int maNV;
    public int maKH;
    public int maGiay;
    public String ngay;
    public String giaHD;
    public int trangThai;

    public ExHoaDon() {
    }

    public ExHoaDon(int maHD, int maNV, int maKH, int maGiay, String ngay, String giaHD, int trangThai) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.maKH = maKH;
        this.maGiay = maGiay;
        this.ngay = ngay;
        this.giaHD = giaHD;
        this.trangThai = trangThai;
    }
}