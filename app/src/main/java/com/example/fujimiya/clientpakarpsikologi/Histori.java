package com.example.fujimiya.clientpakarpsikologi;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Histori extends AppCompatActivity {
    private ArrayList<String> IDuser = new ArrayList<>();
    private ArrayList<String> IDKategori = new ArrayList<>();
    private ArrayList<String> Nilai = new ArrayList<>();
    private ArrayList<String> Tanggal = new ArrayList<>();

    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histori);

        SharedPreferences sp=getSharedPreferences("login", Context.MODE_PRIVATE);
        final String value = sp.getString("id_user", "");

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference hisku = database.getReference("histori_kuis");
        hisku.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(final DataSnapshot child : dataSnapshot.getChildren()){
                    if(child.child("id_user").getValue().toString().equals(value)){
                        DatabaseReference myFirebaseRef = database.getReference("kategori");
                        myFirebaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for(DataSnapshot child2 : dataSnapshot.getChildren()){
                                    if (child2.child("id_kategori").getValue().toString().equals(child.child("id_kategori").getValue().toString())) {
                                        IDuser.add(child.child("id_user").getValue().toString());
                                        IDKategori.add(child2.child("nama_kategori").getValue().toString());
                                        Nilai.add(child.child("nilai").getValue().toString());
                                        Tanggal.add(child.child("tanggal").getValue().toString());
                                    }
                                }
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
//                        Toast.makeText(Histori.this,""+child.child("id_user").getValue().toString(),Toast.LENGTH_SHORT).show();

                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        rvView = findViewById(R.id.rv_main_barang);
        rvView.setHasFixedSize(true);


        rvView.setLayoutManager(new GridLayoutManager(Histori.this, 1));
        adapter = new AdapterHistori(Histori.this,IDuser,IDKategori,Nilai,Tanggal);
        rvView.setAdapter(adapter);
    }
}
