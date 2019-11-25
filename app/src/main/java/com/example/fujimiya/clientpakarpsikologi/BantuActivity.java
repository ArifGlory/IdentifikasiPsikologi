package com.example.fujimiya.clientpakarpsikologi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

import static com.example.fujimiya.clientpakarpsikologi.SharedVariable.changeLang;
import static com.example.fujimiya.clientpakarpsikologi.SharedVariable.locale;

public class BantuActivity extends AppCompatActivity {
    AlertDialog alert;
    private ArrayList<String> id_kat = new ArrayList<String>();
    private ArrayList<String> nama_kat = new ArrayList<String>();
    Button btnChangeLang;
    MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bantu);
        btnChangeLang = findViewById(R.id.btnChangeLang);
        mainActivity = new MainActivity();

       changeLang(this);

        btnChangeLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(BantuActivity.this);
                View promptView = layoutInflater.inflate(R.layout.dialog_change_lang, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(BantuActivity.this);
                alertDialogBuilder.setView(promptView);

                final RadioButton rdEnglish   = promptView.findViewById(R.id.rdEnglish);
                final RadioButton rdIndo      = promptView.findViewById(R.id.rdIndo);
                Button btnChoose        = promptView.findViewById(R.id.btnChoose);

                btnChoose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (rdEnglish.isChecked()){
                            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("LANG", "en").commit();
                            setLangRecreate("en");
                            SharedVariable.activeLang = "en";
                            alert.dismiss();

                        }else if (rdIndo.isChecked()){
                            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("LANG", "in").commit();
                            setLangRecreate("in");
                            SharedVariable.activeLang = "in";
                            alert.dismiss();

                        }
                    }
                });

                alert = alertDialogBuilder.create();
                alert.show();
            }
        });
    }

    public void setLangRecreate(String langval) {
        Configuration config = getBaseContext().getResources().getConfiguration();
        locale = new Locale(langval);
        Locale.setDefault(locale);
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        recreate();
    }

    public void teskuy(View view) {
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

//        startActivity(new Intent(MainActivity.this,TestActivity.class));
        LayoutInflater layoutInflater = LayoutInflater.from(BantuActivity.this);
        View promptView = layoutInflater.inflate(R.layout.dialog_pilih, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(BantuActivity.this);
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
                startActivity(new Intent(BantuActivity.this,TestActivity.class));
                alert.cancel();
            }
        });
        alert = alertDialogBuilder.create();
        alert.show();
    }
}
