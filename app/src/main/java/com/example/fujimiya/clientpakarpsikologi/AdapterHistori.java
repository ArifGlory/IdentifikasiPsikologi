package com.example.fujimiya.clientpakarpsikologi;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by fujimiya on 2/6/19.
 */

public class AdapterHistori extends RecyclerView.Adapter<AdapterHistori.ViewHolder> {
    private ArrayList<String> IDuser;
    private ArrayList<String> IDKategori;
    private ArrayList<String> Nilai;
    private ArrayList<String> Tanggal;
    Context context;

    public AdapterHistori(Context contxt, ArrayList<String> IDuserget, ArrayList<String> IDKategoriget, ArrayList<String> Nilaiget,ArrayList<String> Tanggalget){
        IDuser = IDuserget;
        IDKategori = IDKategoriget;
        Nilai = Nilaiget;
        Tanggal = Tanggalget;
        context = contxt;
    }

    @Override
    public AdapterHistori.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // membuat view baru
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_histori, parent, false);
        // mengeset ukuran view, margin, padding, dan parameter layout lainnya
        AdapterHistori.ViewHolder vh = new AdapterHistori.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AdapterHistori.ViewHolder holder, int position) {

        holder.txt_Titel.setText(IDKategori.get(position));
        holder.txt_Subtitel.setText(Nilai.get(position)+"%");
        holder.txt_TanggalOrder.setText(Tanggal.get(position));
    }

    @Override
    public int getItemCount() {
        return Nilai.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_Titel;
        public TextView txt_Subtitel;
        public TextView txt_TanggalOrder;
        public CardView cvMain;
        public ViewHolder(View v) {
            super(v);
            txt_Titel = (TextView) v.findViewById(R.id.title);
            txt_Subtitel = (TextView) v.findViewById(R.id.subtitle);
            txt_TanggalOrder = (TextView) v.findViewById(R.id.date);
            cvMain = (CardView) v.findViewById(R.id.cv_main);
            cvMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }
    }
}
