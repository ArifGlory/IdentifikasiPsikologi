package com.example.fujimiya.clientpakarpsikologi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class TestActivity extends AppCompatActivity {
    private ArrayList<String> Kategori = new ArrayList<String>();
    private ArrayList<String> Isi = new ArrayList<String>();
    private ArrayList<String> Kunci = new ArrayList<String>();
    private ArrayList<Double> nilai = new ArrayList<Double>();
    private ArrayList<String> id_kat = new ArrayList<String>();
    private ArrayList<String> nama_kat = new ArrayList<String>();
    private ArrayList<String> id_jen = new ArrayList<String>();
    public int posisi = 0;
    TextView txt_nourut,txt_kategori;
    Button btn_nex,btn_sel;
    JustifiedTextView txt_isi;
    RadioButton rYes,rNo;
    RadioGroup rgroub;

    int jumlah = 0;

    AlertDialog alert;
    String id_get_kat = "";

    Double average = 0.0;
    Double hasil = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        txt_nourut = findViewById(R.id.txt_nourut);
        txt_kategori = findViewById(R.id.txt_Kategori);
        txt_isi = findViewById(R.id.txtPernyataan);
        btn_nex = findViewById(R.id.nextBtn);
        btn_sel = findViewById(R.id.selesaiBtn);

        rYes = findViewById(R.id.rdYa);
        rNo = findViewById(R.id.rdTdk);
        rgroub = findViewById(R.id.rGroup);

        btn_sel.setVisibility(View.INVISIBLE);
        btn_nex.setVisibility(View.INVISIBLE);
        posisi = 0;
        rYes.setEnabled(false);
        rNo.setEnabled(false);
        btn_sel.setEnabled(false);

        SharedPreferences sp=getSharedPreferences("kategori", Context.MODE_PRIVATE);
        id_get_kat = sp.getString("id_kategori", "");

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myFirebaseRef = database.getReference("detail_psikolog");
        myFirebaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                rYes.setEnabled(true);
                rNo.setEnabled(true);
                btn_nex.setVisibility(View.VISIBLE);
                btn_sel.setEnabled(false);
                if(!Kategori.isEmpty()){
                    Kategori.clear();
                }
                if(!Isi.isEmpty()){
                    Isi.clear();
                }
                if(!Kunci.isEmpty()){
                    Kunci.clear();
                }

                if(!id_kat.isEmpty()){
                    id_kat.clear();
                }

                if(!nama_kat.isEmpty()){
                    nama_kat.clear();
                }
                if(!id_kat.isEmpty()){
                    id_jen.clear();
                }

                jumlah = 0;
                for(final DataSnapshot child : dataSnapshot.getChildren()) {
                    if (child.child("id_kategori").getValue().toString().equals(id_get_kat)) {
                    final String kategoriku = "";
                    String jenisku = "";
                    DatabaseReference mykateg = database.getReference("kategori");
                    mykateg.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for (final DataSnapshot child2 : dataSnapshot.getChildren()) {
                                if (child2.child("id_kategori").getValue().toString().equals(child.child("id_kategori").getValue().toString())) {
//                        Toast.makeText(TestActivity.this,"ID kat 1 :"+child.child("id_kategori").getValue().toString()+"ID kat 2 : "+child2.child("id_kategori").getValue().toString()+"Nama kat 2 : "+child2.child("nama_kategori").getValue().toString(),Toast.LENGTH_SHORT).show();
//                        nama_kat.add(child2.child("nama_kategori").getValue().toString());

                                    DatabaseReference myjenis = database.getReference("jenis");
                                    myjenis.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            for (DataSnapshot child3 : dataSnapshot.getChildren()) {
                                                if (child3.child("id_jenis").getValue().toString().equals(child.child("id_jenis").getValue().toString())) {
                                                    txt_kategori.setText("" + child2.child("nama_kategori").getValue().toString() + "-" + child3.child("jenis").getValue().toString());
                                                }
//                                                Kategori.add(child2.child("nama_kategori").getValue().toString()+" - " +child3.child("jenis").getValue().toString());
                                                //id_jen.add(child3.child("jenis").getValue().toString());
                                            }
                                            //txt_kategori.setText(""+Kategori.get(posisi));
//                                            txt_kategori.setText(""+nama_kat.get(posisi)+"-"+nama_jenis.get(posisi));
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });
                                }
                            }


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

//                    Kategori.add("ID : "+child.child("id_jenis").getValue().toString()+"Kategori "+child.child("id_kategori").getValue().toString());
                    Isi.add(child.child("psikologis").getValue().toString());
                    id_kat.add(child.child("id_kategori").getValue().toString());
                    id_jen.add(child.child("id_jenis").getValue().toString());
                    Kunci.add(child.getKey());

                    jumlah++;
                }
                }
//                Toast.makeText(TestActivity.this,""+(jumlah+1+" Jumlah Kat :"+Kategori.size()),Toast.LENGTH_SHORT).show();


                if(Isi.size() > 0) {
                    txt_nourut.setText("No : "+(posisi+1)+" dari "+(jumlah));
                    txt_isi.setText("" + Isi.get(posisi));
                }
                if(Isi.size() == 0){
                    txt_isi.setText("Tidak ada soal");
                    rgroub.setVisibility(View.INVISIBLE);
                }
                btn_nex.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public void btn_next(View view) {
//        Toast.makeText(TestActivity.this,"Klik"+posisi,Toast.LENGTH_SHORT).show();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mykateg = database.getReference("kategori");
        mykateg.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(final DataSnapshot child2 : dataSnapshot.getChildren()){
                    if(child2.child("id_kategori").getValue().toString().equals(id_kat.get(posisi))) {
//                        Toast.makeText(TestActivity.this,"ID kat 1 :"+child.child("id_kategori").getValue().toString()+"ID kat 2 : "+child2.child("id_kategori").getValue().toString()+"Nama kat 2 : "+child2.child("nama_kategori").getValue().toString(),Toast.LENGTH_SHORT).show();
//                        nama_kat.add(child2.child("nama_kategori").getValue().toString());

                        DatabaseReference myjenis = database.getReference("jenis");
                        myjenis.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for(DataSnapshot child3 : dataSnapshot.getChildren()){
                                    if(child3.child("id_jenis").getValue().toString().equals(id_jen.get(posisi))) {
                                        txt_kategori.setText(""+child2.child("nama_kategori").getValue().toString()+"-"+child3.child("jenis").getValue().toString());
                                    }
//                                                Kategori.add(child2.child("nama_kategori").getValue().toString()+" - " +child3.child("jenis").getValue().toString());
                                    //id_jen.add(child3.child("jenis").getValue().toString());
                                }
                                //txt_kategori.setText(""+Kategori.get(posisi));
//                                            txt_kategori.setText(""+nama_kat.get(posisi)+"-"+nama_jenis.get(posisi));
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //nilai.add(posisi,hasil);
        rYes.setEnabled(true);
        rNo.setEnabled(true);
        btn_nex.setVisibility(View.INVISIBLE);
        rgroub.clearCheck();
        posisi++;
        if((posisi+1) < Isi.size()){
            txt_nourut.setText("No : "+(posisi+1)+" dari "+(Isi.size()));
//            txt_kategori.setText(""+id_kat.get(posisi)+"-"+id_jen.get(posisi));
            txt_isi.setText(""+Isi.get(posisi));
            btn_nex.setVisibility(View.INVISIBLE);
        }
        if((posisi+1) == Isi.size()){
            txt_nourut.setText("No : "+(posisi+1)+" dari "+(Isi.size()));
            txt_isi.setText(""+Isi.get(posisi));
            btn_sel.setVisibility(View.VISIBLE);
            btn_nex.setVisibility(View.INVISIBLE);
        }

    }

    public void onRadioButtonClicked(View view) {
        Double hasil = 0.0;
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rdYa:
                if (checked)
                    // YA
                    hasil = 0.013;
//                    nilai.set(posisi,hasil);
                    nilai.add(hasil);
                    Toast.makeText(TestActivity.this,"YA"+nilai.size(),Toast.LENGTH_SHORT).show();
//                    rYes.setEnabled(false);
//                    rNo.setEnabled(false);
                    btn_sel.setEnabled(true);
                    if((posisi+1) < Isi.size()) {
                        btn_nex.setVisibility(View.VISIBLE);
                    }
                    if((posisi+1) == Isi.size()){
                        btn_sel.setVisibility(View.VISIBLE);
                        btn_nex.setVisibility(View.INVISIBLE);
                    }
                    break;
            case R.id.rdTdk:
                if (checked)
                    // TIDAK
                    hasil =  0.0 * 0.013;
//                    nilai.set(posisi,hasil);
                    nilai.add(hasil);
                    Toast.makeText(TestActivity.this,"TIDAK",Toast.LENGTH_SHORT).show();
//                    rYes.setEnabled(false);
//                    rNo.setEnabled(false);
                    btn_sel.setEnabled(true);
                    if((posisi+1) < Isi.size()) {
                        btn_nex.setVisibility(View.VISIBLE);
                    }
                    if((posisi+1) == Isi.size()){
                        btn_sel.setVisibility(View.VISIBLE);
                        btn_nex.setVisibility(View.INVISIBLE);
                    }
                    break;
        }
    }

    public void radiobutton(View view) {
    }

    public void btn_selesai(View view) {
        for(int a=0; a < nilai.size(); a++){
            average+=nilai.get(a);

            //Toast.makeText(TestActivity.this,"Nilai : "+nilai.get(a)+", Kategori : "+id_kat.get(a),Toast.LENGTH_SHORT).show();
        }

        hasil = (((average)/nilai.size())/0.013)*100;


            LayoutInflater layoutInflater = LayoutInflater.from(TestActivity.this);
            View promptView = layoutInflater.inflate(R.layout.hasil, null);
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TestActivity.this);
            alertDialogBuilder.setView(promptView);

            final TextView txthasil = promptView.findViewById(R.id.hasil);
            final Button btnselesai = promptView.findViewById(R.id.simpan);
            btnselesai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference mykateg = database.getReference("kategori");
            mykateg.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for(final DataSnapshot child2 : dataSnapshot.getChildren()){
                        if(child2.child("id_kategori").getValue().toString().equals(id_get_kat)) {
//                        Toast.makeText(TestActivity.this,"ID kat 1 :"+child.child("id_kategori").getValue().toString()+"ID kat 2 : "+child2.child("id_kategori").getValue().toString()+"Nama kat 2 : "+child2.child("nama_kategori").getValue().toString(),Toast.LENGTH_SHORT).show();
//                        nama_kat.add(child2.child("nama_kategori").getValue().toString());
                            if(hasil >= 50) {
                                txthasil.setText("Nilai Tes : "+new DecimalFormat("##.##").format(hasil)+"%\nMasuk Kategori Memadai : \n "+child2.child("heigh").getValue().toString());

                            }
                            if(hasil < 50){
                                txthasil.setText("Nilai Tes : "+new DecimalFormat("##.##").format(hasil)+"%\nMasuk Kategori Perlu Pengembangan : \n"+child2.child("low").getValue().toString());
                            }

                        }
                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            alert = alertDialogBuilder.create();
            alert.show();

        SharedPreferences sp=getSharedPreferences("login", Context.MODE_PRIVATE);
        String value = sp.getString("id_user", "");

        Random rand = new Random();
        Map mParent = new HashMap();
        mParent.put("id_user", "" + value);
        mParent.put("id_kategori", "" + id_get_kat);
        mParent.put("nilai", "" + String.format("%.2f", hasil));
        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);
        mParent.put("tanggal", "" + thisDate);
        DatabaseReference ref = database.getReference("histori_kuis");
        ref.push().setValue(mParent).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(TestActivity.this,"Data berhasil disimpan",Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getApplicationContext(), LoginActivity.class));


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(TestActivity.this,"Data gagal disimpan",Toast.LENGTH_SHORT).show();
            }
        });
        //Toast.makeText(TestActivity.this,""+(((average/nilai.size())/0.013) * 100),Toast.LENGTH_SHORT).show();
    }

}
