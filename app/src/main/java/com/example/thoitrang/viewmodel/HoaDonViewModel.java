package com.example.thoitrang.viewmodel;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.thoitrang.dao.GiayDAO;
import com.example.thoitrang.dao.HoaDonDAO;
import com.example.thoitrang.database.AppDatabase;
import com.example.thoitrang.dto.ItemDto;
import com.example.thoitrang.model.Cart;
import com.example.thoitrang.model.Giay;
import com.example.thoitrang.model.HoaDon;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HoaDonViewModel extends ViewModel {

    private AppDatabase appDatabase;

    private MutableLiveData<Integer> hoaDonChanged = new MutableLiveData<>(0);
    private HoaDonDAO hoaDonDAO;
    private GiayDAO giayDAO;

    private Integer getGia(List<Cart> lists) {
        int gia = 0;
        for (Cart cart : lists) {
            Giay giay = giayDAO.getID(String.valueOf(cart.giay.maLoai));
            gia += stringToInt(giay.giaMua) * cart.soLuong;
        }
        return gia;
    }

    private Integer stringToInt(String giaHD) {
        try {
            return Integer.parseInt(giaHD);
        } catch (Exception e) {
            return 0;
        }
    }


    @SuppressLint("NewApi")
    private String getCurrentDate() {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDate.format(formatter);
    }

    public LiveData<Integer> observeHoaDonChanged() {
        return hoaDonChanged;
    }

    public void setDao(HoaDonDAO hoaDonDAO, GiayDAO giayDAO) {
        this.hoaDonDAO = hoaDonDAO;
        this.giayDAO = giayDAO;
    }

    public void setAppDatabase(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    public void createHoaDon(List<Cart> lists, int maNV, int maKhachHang, String tenKhachHang, int trangthai) {
        if (hoaDonDAO != null) {
            HoaDon hoaDon = new HoaDon();
            hoaDon.setMaNV(maNV);
            hoaDon.setMaKH(maKhachHang);
            hoaDon.setTenKH(tenKhachHang);
            hoaDon.setNgay(getCurrentDate());
            hoaDon.setGiaHD(String.valueOf(getGia(lists)));
            hoaDon.setTrangThai(trangthai);
            List<ItemDto> itemDtos = new ArrayList<>();
            for (Cart cart : lists) {
                itemDtos.add(new ItemDto(cart.giay.maLoai, cart.soLuong));
            }
            hoaDon.setItems(itemDtos);
            appDatabase.appDao().insert(hoaDon);
        }
    }
    public void saveHoaDon(int maHD,List<Cart> lists, int maNV, int maKhachHang, String tenKhachHang, int trangthai) {
        if (hoaDonDAO != null) {
            HoaDon hoaDon = new HoaDon(maHD);
            hoaDon.setMaNV(maNV);
            hoaDon.setMaKH(maKhachHang);
            hoaDon.setTenKH(tenKhachHang);
            hoaDon.setNgay(getCurrentDate());
            hoaDon.setGiaHD(String.valueOf(getGia(lists)));
            hoaDon.setTrangThai(trangthai);
            List<ItemDto> itemDtos = new ArrayList<>();
            for (Cart cart : lists) {
                itemDtos.add(new ItemDto(cart.giay.maLoai, cart.soLuong));
            }
            hoaDon.setItems(itemDtos);
            appDatabase.appDao().insert(hoaDon);
        }
    }

}
