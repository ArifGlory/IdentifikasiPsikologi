package com.example.fujimiya.clientpakarpsikologi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetailKategori extends AppCompatActivity {

    TextView tvCatTitle,tvCatDescription,tvCatSkill,tvCatHigh,tvCatLow;
    Intent i;
    private String catTitle,catDesc,catSkill,catHigh,catLow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kategori);

        i = getIntent();
        catTitle   = i.getStringExtra("catTitle");
        catDesc   = i.getStringExtra("catDesc");
        catSkill   = i.getStringExtra("catSkill");
        catHigh   = i.getStringExtra("catHigh");
        catLow   = i.getStringExtra("catLow");


        tvCatTitle          = findViewById(R.id.tvCatTitle);
        tvCatDescription    = findViewById(R.id.tvCatDescription);
        tvCatSkill          = findViewById(R.id.tvCatSkill);
        tvCatHigh           = findViewById(R.id.tvCatHighScorer);
        tvCatLow            = findViewById(R.id.tvCatLowScorer);

        tvCatTitle.setText(catTitle);
        tvCatDescription.setText(catDesc);
        tvCatSkill.setText(catSkill);
        tvCatHigh.setText(catHigh);
        tvCatLow.setText(catLow);
    }
}
