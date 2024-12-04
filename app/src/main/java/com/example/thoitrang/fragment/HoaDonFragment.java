package com.example.thoitrang.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thoitrang.R;
import com.example.thoitrang.adapter.HoaDonAdapter;
import com.example.thoitrang.database.AppDatabase;
import com.example.thoitrang.databinding.FragmentHoaDon2Binding;
import com.example.thoitrang.dto.ItemDto;
import com.example.thoitrang.model.HoaDon;

import java.util.ArrayList;
import java.util.List;

public class HoaDonFragment extends Fragment {

    public static final String MA_HD = "maHD";

    FragmentHoaDon2Binding binding;
    AppDatabase appDatabase;
    HoaDonAdapter hoaDonAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHoaDon2Binding.inflate(inflater, container, false);
        appDatabase = AppDatabase.getInstance(requireActivity());
        hoaDonAdapter = new HoaDonAdapter(new ArrayList<>(), hoaDon -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_hoa_don);
            Bundle bundle = new Bundle();
            bundle.putInt(MA_HD, hoaDon.getMaHD());
            navController.navigate(R.id.action_hoaDonFragment_to_editHoaDonFragment, bundle);
        }, appDatabase.appDao()::delete);
        setEvents();
        binding.rvHoaDon.setAdapter(hoaDonAdapter);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        hoaDonAdapter.setHoaDonList(appDatabase.appDao().getAllHoaDon());
    }

    private void setEvents() {
        binding.hoaDonFab.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_hoa_don);
            navController.navigate(R.id.action_hoaDonFragment_to_taoHoaDonFragment);
        });
    }
}