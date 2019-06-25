package com.example.fujimiya.clientpakarpsikologi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        defaultLang =  Locale.getDefault().getDisplayLanguage();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mykateg = database.getReference("kategori");
        mykateg.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(final DataSnapshot child2 : dataSnapshot.getChildren()){
                    id_kat.add(child2.child("id_kategori").getValue().toString());
                    nama_kat.add(child2.child("nama_kategori").getValue().toString());
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onResume() {

        String activelang = Locale.getDefault().getDisplayLanguage();
        Log.d("activeLang : ",activelang);
        if (!defaultLang.equals(activelang)){
            recreate();
        }

        super.onResume();
       // changeLang(MainActivity.this);
    }

    public void kategori(View view) {
        startActivity(new Intent(getApplicationContext(), InfoKategoriActivity.class));
    }


    public void test(View view) {
//        startActivity(new Intent(MainActivity.this,TestActivity.class));
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
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




        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, nama_kat);
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
        alert.show();
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
        startActivity(new Intent(getApplicationContext(), Histori.class));
    }

    public void tentang(View view) {
        startActivity(new Intent(getApplicationContext(), TentangActivity.class));
    }

    public void bantuan(View view) {
        startActivity(new Intent(getApplicationContext(), BantuActivity.class));
    }

}
