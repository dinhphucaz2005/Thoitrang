package com.example.thoitrang.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thoitrang.databinding.LayoutHoaDonItemBinding;
import com.example.thoitrang.model.HoaDon;
import com.example.thoitrang.util.Callback;

import java.util.List;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHolder> {

    private final List<HoaDon> list;
    private final Callback<HoaDon> callback;
    private final Callback<HoaDon> delete;

    public HoaDonAdapter(List<HoaDon> list, Callback<HoaDon> callback, Callback<HoaDon> delete) {
        this.list = list;
        this.callback = callback;
        this.delete = delete;
    }

    @NonNull
    @Override
    public HoaDonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutHoaDonItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonAdapter.ViewHolder holder, int position) {
        HoaDon hoaDon = list.get(position);
        holder.binding.tvMaHoaDon.setText("Mã hoá đơn: " + hoaDon.getMaHD());
        holder.binding.tvTenKhachHang.setText("Tên khách hàng: " + hoaDon.getTenKH());
        holder.binding.tvTrangThaiHoaDon.setText("Trạng thái: " + hoaDon.getTrangThaiString());
        holder.binding.tvGia.setText("Tổng: " + hoaDon.getGiaHD() + " VNĐ");
        holder.binding.tvNgayMua.setText(hoaDon.getNgay());
        holder.binding.getRoot().setOnLongClickListener(v -> {
            callback.onComplete(hoaDon);
            return true;
        });
        holder.binding.btnDeleteHoaDon.setOnClickListener(v -> {
            notifyItemRemoved(position);
            list.remove(hoaDon);
            delete.onComplete(hoaDon);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setHoaDonList(List<HoaDon> allHoaDon) {
        this.list.clear();
        this.list.addAll(allHoaDon);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final LayoutHoaDonItemBinding binding;

        public ViewHolder(LayoutHoaDonItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
