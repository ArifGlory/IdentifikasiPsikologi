package com.example.fujimiya.clientpakarpsikologi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.Locale;

public class PilihKategoriActivity extends AppCompatActivity {

    RelativeLayout rlAutonomy,rlEnvironment,rlSelf,rlPurpose,rlPositive,rlPersonal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_kategori);

        rlAutonomy = findViewById(R.id.rlAutonomy);
        rlEnvironment = findViewById(R.id.rlEnvironment);
        rlSelf = findViewById(R.id.rlSelf);
        rlPurpose = findViewById(R.id.rlPurpose);
        rlPositive = findViewById(R.id.rlPositive);
        rlPersonal = findViewById(R.id.rlPersonal);
        final Intent intent = new Intent(getApplicationContext(),TestActivity.class);


        rlAutonomy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp=getSharedPreferences("kategori", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed=sp.edit();

                String nama_kategori = "Autonomy";
                nama_kategori = convertKategoriToActiveLang(nama_kategori);
                Log.d("pilihkategori","nama : "+nama_kategori);

                ed.putString("id_kategori", "13");
                ed.putString("nama_kategori", nama_kategori);
                ed.commit();

                startActivity(intent);
            }
        });
        rlEnvironment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp=getSharedPreferences("kategori", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed=sp.edit();

                String nama_kategori = "Enviromental Mastery";
                nama_kategori = convertKategoriToActiveLang(nama_kategori);

                ed.putString("id_kategori", "1714");
                ed.putString("nama_kategori", nama_kategori);
                ed.commit();

                startActivity(intent);
            }
        });
        rlPurpose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp=getSharedPreferences("kategori", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed=sp.edit();

                String nama_kategori = "Purpose In Life";
                nama_kategori = convertKategoriToActiveLang(nama_kategori);

                ed.putString("id_kategori", "1929");
                ed.putString("nama_kategori", nama_kategori);
                ed.commit();

                startActivity(intent);
            }
        });
        rlSelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp=getSharedPreferences("kategori", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed=sp.edit();

                String nama_kategori = "Self Acceptance ";
                nama_kategori = convertKategoriToActiveLang(nama_kategori);

                ed.putString("id_kategori", "362");
                ed.putString("nama_kategori", nama_kategori);
                ed.commit();

                startActivity(intent);
            }
        });
        rlPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp=getSharedPreferences("kategori", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed=sp.edit();

                String nama_kategori = "Positive Relationships With Others";
                nama_kategori = convertKategoriToActiveLang(nama_kategori);

                ed.putString("id_kategori", "1488");
                ed.putString("nama_kategori", nama_kategori);
                ed.commit();

                startActivity(intent);
            }
        });
        rlPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp=getSharedPreferences("kategori", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed=sp.edit();

                String nama_kategori = "Personal Growth";
                nama_kategori = convertKategoriToActiveLang(nama_kategori);

                ed.putString("id_kategori", "1974");
                ed.putString("nama_kategori", nama_kategori);
                ed.commit();

                startActivity(intent);
            }
        });

    }

    private String convertKategoriToActiveLang(String kategori){
        String activelang = Locale.getDefault().getDisplayLanguage();
        Log.d("convertKategori","activeLang ketika convert : "+activelang);

        String systemLang   = Locale.getDefault().getDisplayLanguage();
        Resources res      = getApplicationContext().getResources();
        int resId;
        String catTitle = "";

        switch (kategori){
            case "Autonomy":
                catTitle = "cat_autonomy";
                resId = res.getIdentifier(catTitle,"string",getApplicationContext().getPackageName());
                kategori = res.getString(resId);
                break;
            case "Enviromental Mastery":
                catTitle = "cat_environment";
                resId = res.getIdentifier(catTitle,"string",getApplicationContext().getPackageName());
                kategori = res.getString(resId);
                break;
            case "Self Acceptance ":
                catTitle = "cat_self";
                resId = res.getIdentifier(catTitle,"string",getApplicationContext().getPackageName());
                kategori = res.getString(resId);
                Log.d("self acc:",kategori);

                   /* if (systemLang.equals("English")){
                        kategori = "Self Acceptance";
                    }else {
                        kategori = "Penerimaan Diri";
                    }*/

                break;
            case "Purpose In Life":
                catTitle = "cat_purpose";
                resId = res.getIdentifier(catTitle,"string",getApplicationContext().getPackageName());
                kategori = res.getString(resId);
                break;
            case "Positive Relationships With Others":
                catTitle = "cat_positive";
                resId = res.getIdentifier(catTitle,"string",getApplicationContext().getPackageName());
                kategori = res.getString(resId);
                break;
            case "Personal Growth":
                catTitle = "cat_personal";
                resId = res.getIdentifier(catTitle,"string",getApplicationContext().getPackageName());
                kategori = res.getString(resId);
                break;
        }


        return kategori;
    }
}
