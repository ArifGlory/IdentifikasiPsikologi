package com.example.fujimiya.clientpakarpsikologi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Shader;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

import static com.example.fujimiya.clientpakarpsikologi.SharedVariable.changeLang;
import static com.example.fujimiya.clientpakarpsikologi.SharedVariable.locale;

public class MainActivity extends AppCompatActivity {
    AlertDialog alert;
    private ArrayList<String> id_kat = new ArrayList<String>();
    private ArrayList<String> nama_kat = new ArrayList<String>();
    private String defaultLang;
    FirebaseDatabase database;
    ArrayAdapter<String> adapter;
    TextView tvAbout,tvPsyiden,tvInformasi,tvHelp,tvHistory,tvLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        defaultLang =  Locale.getDefault().getDisplayLanguage();
        database = FirebaseDatabase.getInstance();
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, nama_kat);

        tvAbout = findViewById(R.id.tvAbout);
        tvPsyiden = findViewById(R.id.tvPsyiden);
        tvInformasi = findViewById(R.id.tvInformasi);
        tvHelp = findViewById(R.id.tvHelp);
        tvHistory = findViewById(R.id.tvHistory);
        tvLogout = findViewById(R.id.tvLogout);

        changeLang(this);

        getDataKategori();
    }

    private void getDataKategori(){
        DatabaseReference mykateg = database.getReference("kategori");
        mykateg.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                nama_kat.clear();
                id_kat.clear();
                String activelang = Locale.getDefault().getDisplayLanguage();
                Log.d("getKategori"," bahasa saat get kategori : "+activelang);


                for(final DataSnapshot child2 : dataSnapshot.getChildren()){
                    id_kat.add(child2.child("id_kategori").getValue().toString());

                    String nama_kategori = child2.child("nama_kategori").getValue().toString();
                    String id_kategori = child2.child("id_kategori").getValue().toString();

                    Log.d("kategori:",nama_kategori+" & id nya : "+id_kategori);
                    nama_kategori = convertKategoriToActiveLang(nama_kategori);
                    nama_kat.add(nama_kategori);
                }
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private String convertKategoriToActiveLang(String kategori){
        String activelang = Locale.getDefault().getDisplayLanguage();
        Log.d("convertKategori","activeLang ketika convert : "+activelang);

        String systemLang   = Locale.getDefault().getDisplayLanguage();
        Resources  res      = getApplicationContext().getResources();
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

    @Override
    protected void onResume() {
        super.onResume();
        String activeLang = SharedVariable.activeLang;
        Log.d("onResume:","active lang "+activeLang);
        setView(activeLang);


    }

    private void setView(String activeLang){
        //tvAbout,tvPsyiden,tvInformasi,tvHelp,tvHistory,tvLogout;
        if (activeLang.equals("en")){
            tvAbout.setText("About");
            tvPsyiden.setText("Psycological Identification");
            tvInformasi.setText("Information on Psycological Walfare Categories");
            tvHelp.setText("Help");
            tvHistory.setText("History");
            tvLogout.setText("Logout");
        }else if (activeLang.equals("in")){
            tvAbout.setText("Tentang");
            tvPsyiden.setText("Identifikasi Psikologis");
            tvInformasi.setText("Informasi Kategori Kesejahteraan Psikologis");
            tvHelp.setText("Bantuan");
            tvHistory.setText("Riwayat");
            tvLogout.setText("Keluar");
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);


    }

    public void setMainLangRecreate(String langval) {
        Log.d("setlang",": "+langval);
        Configuration config = MainActivity.this.getResources().getConfiguration();
        locale = new Locale(langval);
        Locale.setDefault(locale);
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        recreate();
    }

    public void kategori(View view) {
        startActivity(new Intent(getApplicationContext(), InfoKategoriActivity.class));
    }


    public void test(View view) {
        startActivity(new Intent(MainActivity.this,PilihKategoriActivity.class));
      /*  LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        View promptView = layoutInflater.inflate(R.layout.dialog_pilih, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptView);

        Spinner spinner = promptView.findViewById(R.id.kategori);

//        String[] germanFeminine = {
//                "Karin",
//                "Ingrid", "Helga",
//                "Renate",
//                "Elke",
//                "Ursula",
//                "Erika",
//                "Christa",
//                "Gisela",
//                "Monika"};



        *//*adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, nama_kat);*//*
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this,""+id_kat.get(position),Toast.LENGTH_SHORT).show();
                SharedPreferences sp=getSharedPreferences("kategori", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed=sp.edit();
                ed.putString("id_kategori", id_kat.get(position));
                ed.putString("nama_kategori", nama_kat.get(position));
                ed.commit();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Button ok = promptView.findViewById(R.id.simpan);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,TestActivity.class));
                alert.cancel();
            }
        });
        alert = alertDialogBuilder.create();
        alert.show();*/
    }

    public void keluar(View view) {
        SharedPreferences sp=getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed=sp.edit();
        ed.putString("id_user", "");
        ed.putString("nama", "");
        ed.putString("email", "");
        ed.commit();

        finish();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    public void histori(View view) {
       // startActivity(new Intent(getApplicationContext(), TranslateActivity.class));
        startActivity(new Intent(getApplicationContext(), Histori.class));
    }

    public void tentang(View view) {
        startActivity(new Intent(getApplicationContext(), TentangActivity.class));
    }

    public void bantuan(View view) {
        startActivity(new Intent(getApplicationContext(), BantuActivity.class));
    }

}
