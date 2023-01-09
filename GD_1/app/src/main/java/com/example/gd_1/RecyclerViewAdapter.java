package com.example.gd_1;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private  final List<MyItems> items;
    private  final Context context;

    public RecyclerViewAdapter(List<MyItems> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder((LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_adapter_layout,null)));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {

        MyItems myItems = items.get(position);
        holder.kullanici.setText(myItems.getKullanıcı());
        holder.durum.setText(myItems.getDurum());
        holder.mesaj.setText(myItems.getMesaj());
        holder.saglik.setText(myItems.getSaglik());
        holder.afet.setText(myItems.getAfet());
        holder.konum.setText(myItems.getKonum());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView kullanici,mesaj,afet,durum,saglik,konum;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            kullanici=itemView.findViewById(R.id.klnctxt);
            mesaj=itemView.findViewById(R.id.msjtxt);
            afet=itemView.findViewById(R.id.afttxt);
            durum=itemView.findViewById(R.id.drmtxt);
            saglik=itemView.findViewById(R.id.sagliktxt);
            konum=itemView.findViewById(R.id.knmtxt);

        }
    }
}