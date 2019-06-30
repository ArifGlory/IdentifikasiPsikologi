package com.example.fujimiya.clientpakarpsikologi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.fujimiya.clientpakarpsikologi.adapter.AdapterKategori;

public class InfoKategoriActivity extends AppCompatActivity {

    AdapterKategori adapterKategori;
    RecyclerView rvKategori;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_kategori);

        rvKategori      = findViewById(R.id.rvKategori);
        adapterKategori = new AdapterKategori(this);

        rvKategori.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvKategori.setHasFixedSize(true);
        rvKategori.setItemAnimator(new DefaultItemAnimator());
        rvKategori.setAdapter(adapterKategori);

    }
}
