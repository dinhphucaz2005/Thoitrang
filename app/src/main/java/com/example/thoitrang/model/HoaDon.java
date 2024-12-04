package com.example.thoitrang.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.thoitrang.dto.ItemDto;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "hoa_don")
public class HoaDon {


    @ColumnInfo(name = "maHD")
    @PrimaryKey(autoGenerate = true)
    private int maHD = 0;
    private int maNV;
    private int maKH;
    private String tenKH;

    public HoaDon(int maHD) {
        super();
        this.maHD = maHD;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    private List<ItemDto> items;
    private String ngay;

    public String getGiaHD() {
        return giaHD;
    }

    public void setGiaHD(String giaHD) {
        this.giaHD = giaHD;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getTrangThaiString() {
        if (trangThai == 0) {
            return "Chưa thanh toán";
        } else {
            return "Đã thanh toán";
        }
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    private String giaHD;
    private int trangThai;

    public HoaDon() {
        this.maNV = 0;
        this.maKH = 0;
        this.items = new ArrayList<>();
        this.ngay = "";
        this.tenKH = "";
        this.giaHD = "";
        this.trangThai = 0;
    }
}
