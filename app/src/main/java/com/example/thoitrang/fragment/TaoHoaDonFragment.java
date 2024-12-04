package com.example.thoitrang.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.thoitrang.R;
import com.example.thoitrang.adapter.CartAdapter;
import com.example.thoitrang.adapter.KhachHangSpinnerAdapter;
import com.example.thoitrang.adapter.NewGiayAdapter;
import com.example.thoitrang.adapter.NhanVienSpinnerAdapter;
import com.example.thoitrang.dao.GiayDAO;
import com.example.thoitrang.dao.KhachHangDAO;
import com.example.thoitrang.dao.NhanVienDAO;
import com.example.thoitrang.database.AppDatabase;
import com.example.thoitrang.databinding.DialogSearchItemBinding;
import com.example.thoitrang.databinding.FragmentTaoHoaDonBinding;
import com.example.thoitrang.model.Cart;
import com.example.thoitrang.model.Giay;
import com.example.thoitrang.model.HoaDon;
import com.example.thoitrang.model.KhachHang;
import com.example.thoitrang.model.LoaiGiay;
import com.example.thoitrang.model.NhanVien;
import com.example.thoitrang.viewmodel.HoaDonViewModel;

import java.util.ArrayList;
import java.util.List;


public class TaoHoaDonFragment extends Fragment {

    FragmentTaoHoaDonBinding binding;
    AppDatabase appDatabase;
    NewGiayAdapter newGiayAdapter;
    GiayDAO giayDAO;
    ArrayList<Giay> giays;
    Dialog dialog;
    CartAdapter cartAdapter;
    HoaDonViewModel hoaDonViewModel;

    KhachHangSpinnerAdapter khachHangSpinnerAdapter;
    ArrayList<KhachHang> listKhachHang = new ArrayList<>();
    KhachHangDAO khachHangDAO;


    NhanVienSpinnerAdapter nhanVienSpinnerAdapter;
    NhanVienDAO NhanVienDAO;
    ArrayList<NhanVien> listNhanVien = new ArrayList<>();

    int maKhachHang;
    String tenKhachHang;
    int maNV;


    private Integer stringToInt(String giaHD) {
        try {
            return Integer.parseInt(giaHD);
        } catch (Exception e) {
            return 0;
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTaoHoaDonBinding.inflate(inflater, container, false);
        appDatabase = AppDatabase.getInstance(requireActivity());
        hoaDonViewModel = new ViewModelProvider(requireActivity()).get(HoaDonViewModel.class);
        hoaDonViewModel.setAppDatabase(appDatabase);
        cartAdapter = new CartAdapter(new ArrayList<>(), lists -> {
            int gia = 0;
            for (Cart cart : lists) {
                gia += stringToInt(cart.giay.giaMua) * cart.soLuong;
            }
            binding.tvSumPrice.setText("Tổng hoá đơn: " + gia + " VND");
        }, message -> Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show());
        binding.rvCart.setAdapter(cartAdapter);

        newGiayAdapter = new NewGiayAdapter(new ArrayList<>(), giay -> {
            cartAdapter.add(new Cart(giay));
            dialog.dismiss();
        });
        giayDAO = new GiayDAO(requireActivity());
        giays = (ArrayList<Giay>) giayDAO.getAll();

        khachHangDAO = new KhachHangDAO(requireActivity());
        listKhachHang.add(new KhachHang(0, "Chọn khách hàng", "", ""));
        listKhachHang.addAll(khachHangDAO.getAll());
        khachHangSpinnerAdapter = new KhachHangSpinnerAdapter(requireActivity(), listKhachHang);
        binding.spMaKH.setAdapter(khachHangSpinnerAdapter);
        binding.spMaKH.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maKhachHang = listKhachHang.get(position).maKH;
                tenKhachHang = listKhachHang.get(position).tenKH;
                Toast.makeText(requireActivity(), "Chọn" + listKhachHang.get(position).tenKH, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        NhanVienDAO = new NhanVienDAO(requireActivity());
        listNhanVien.add(new NhanVien(0, "Chọn nhân viên", "", "", "", new byte[]{}));
        listNhanVien.addAll(NhanVienDAO.getAll());
        nhanVienSpinnerAdapter = new NhanVienSpinnerAdapter(requireActivity(), listNhanVien);
        binding.spMaNV.setAdapter(nhanVienSpinnerAdapter);
        binding.spMaNV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maNV = listNhanVien.get(position).maNV;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        setEvents();
        return binding.getRoot();
    }

    private void setEvents() {
        binding.btnBack.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_hoa_don);
            navController.popBackStack();
        });
        binding.btnAddProductToCart.setOnClickListener(v -> createAddProductToCartDialog());
        binding.btnTaoHoaDon.setOnClickListener(v -> {
            int trangthai = 0;
            if (binding.chkTrangThai.isChecked()) {
                trangthai = 1;
            }
            hoaDonViewModel.createHoaDon(cartAdapter.getLists(), maNV, maKhachHang, tenKhachHang, trangthai);
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_hoa_don);
            navController.popBackStack();
        });
    }


    private void createAddProductToCartDialog() {
        dialog = new Dialog(requireActivity());


        DialogSearchItemBinding dialogBinding = DialogSearchItemBinding.inflate(getLayoutInflater());
        dialog.setContentView(dialogBinding.getRoot());
        dialogBinding.lvGiay.setAdapter(newGiayAdapter);
        newGiayAdapter.setLists(giays);

        dialogBinding.imgSearchGiay.setOnClickListener(v -> {
            if (giays.isEmpty()) return;
            String search = dialogBinding.edSearchGiay.getText().toString();
            ArrayList<Giay> searchList = new ArrayList<>();
            for (Giay giay : giays) {
                if (giay.tenGiay.toLowerCase().contains(search.toLowerCase())) {
                    searchList.add(giay);
                }
            }
            newGiayAdapter.setLists(searchList);
        });

        dialog.show();
        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }
}