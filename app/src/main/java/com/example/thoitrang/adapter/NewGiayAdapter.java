package com.example.thoitrang.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thoitrang.databinding.LayoutLoaiGiayItemBinding;
import com.example.thoitrang.model.Giay;
import com.example.thoitrang.util.Callback;

import java.util.ArrayList;
import java.util.List;

public class NewGiayAdapter extends RecyclerView.Adapter<NewGiayAdapter.ViewHolder> {

    private final List<Giay> lists;
    private final Callback<Giay> callback;

    public NewGiayAdapter(List<Giay> lists, Callback<Giay> callback) {
        this.lists = lists;
        this.callback = callback;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setLists(ArrayList<Giay> searchList) {
        this.lists.clear();
        this.lists.addAll(searchList);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final LayoutLoaiGiayItemBinding binding;

        public ViewHolder(LayoutLoaiGiayItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public NewGiayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutLoaiGiayItemBinding binding = LayoutLoaiGiayItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NewGiayAdapter.ViewHolder holder, int position) {
        Giay loaiGiay = this.lists.get(position);
        holder.binding.tvMaLoaiGiaySp.setText(loaiGiay.maGiay + ". ");
        holder.binding.tvTenLoaiGiaySp.setText(loaiGiay.tenGiay);
        holder.binding.getRoot().setOnClickListener(v -> callback.onComplete(loaiGiay));
    }

    @Override
    public int getItemCount() {
        return this.lists.size();
    }
}
