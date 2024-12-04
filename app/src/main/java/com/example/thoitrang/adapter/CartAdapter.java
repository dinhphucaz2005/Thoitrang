package com.example.thoitrang.adapter;

import android.annotation.SuppressLint;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thoitrang.databinding.LayoutCartItemBinding;
import com.example.thoitrang.model.Cart;
import com.example.thoitrang.model.LoaiGiay;
import com.example.thoitrang.util.Callback;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private final List<Cart> lists;
    private final Callback<List<Cart>> onCartListener;
    private final Callback<String> message;

    public CartAdapter(List<Cart> lists, Callback<List<Cart>> onCartListener, Callback<String> message) {
        for (Cart cart : lists) {
            if (cart.giay.soLuong.equals("0")) {
                lists.remove(cart);
            }
        }
        this.lists = lists;
        this.onCartListener = onCartListener;
        this.message = message;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutCartItemBinding binding = LayoutCartItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        Cart cart = this.lists.get(position);
        holder.binding.tvLoaiGiay.setText(cart.giay.maLoai + ". " + cart.giay.tenGiay);
        holder.binding.tvSoLuong.setText(String.valueOf(cart.soLuong));
        holder.binding.tvDonGia.setText(String.valueOf(cart.giay.giaMua));
        holder.binding.btnMinus.setOnClickListener(v -> {
            if (cart.soLuong > 0) {
                cart.soLuong--;
                onCartListener.onComplete(lists);
                notifyItemChanged(position);
            }
        });
        holder.binding.btnPlus.setOnClickListener(v -> {
            if (String.valueOf(cart.soLuong).equals(cart.giay.soLuong)) {
                message.onComplete("Số lượng giày đã đạt tối đa");
                return;
            }
            cart.soLuong++;
            onCartListener.onComplete(lists);
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public void add(Cart cart) {
        for (Cart c : lists) {
            if (c.giay.maGiay == cart.giay.maGiay) {
                c.soLuong++;
                notifyItemChanged(lists.indexOf(c));
                return;
            }
        }
        lists.add(cart);
        notifyItemInserted(lists.size() - 1);
    }

    public List<Cart> getLists() {
        return this.lists;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final LayoutCartItemBinding binding;

        public ViewHolder(LayoutCartItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
