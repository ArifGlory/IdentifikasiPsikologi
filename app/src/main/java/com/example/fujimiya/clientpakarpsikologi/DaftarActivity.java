package com.example.fujimiya.clientpakarpsikologi;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DaftarActivity extends AppCompatActivity {
    EditText txt_nama,txt_email,txt_nope,txt_alamat,txt_password;
    String nama,email,nope,alamat,password = "";
    String validasi = "no";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        txt_nama = findViewById(R.id.txtnama);
        txt_email = findViewById(R.id.txtemail);
        txt_nope = findViewById(R.id.txttelp);
        txt_alamat = findViewById(R.id.txtalamat);
        txt_password = findViewById(R.id.txtpassword);
    }

    public void daftar(View view) {
        if(!txt_nama.getText().toString().equals("") && !txt_alamat.getText().toString().equals("") && !txt_nope.getText().toString().equals("") && !txt_email.getText().toString().equals("") && !txt_password.getText().toString().equals("")){
            nama = txt_nama.getText().toString();
            alamat = txt_alamat.getText().toString();
            nope = txt_nope.getText().toString();
            email = txt_email.getText().toString();
            password = txt_password.getText().toString();
            validasi = "ok";
            ///Toast.makeText(this,"Data terisi : "+nama,Toast.LENGTH_SHORT).show();
            //addEmployee(nama,alamat,nope,email,password);

            Random rand = new Random();
            Map mParent = new HashMap();
            mParent.put("id_user", "" + rand.nextInt(20) + ""+rand.nextInt(100));
            mParent.put("nama", "" + nama);
            mParent.put("alamat", "" + alamat);
            mParent.put("nope", "" + nope);
            mParent.put("email", "" + email);
            mParent.put("password", "" + password);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference("user_psikolog");
            ref.push().setValue(mParent).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(DaftarActivity.this,"Data berhasil disimpan",Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(DaftarActivity.this,"Data gagal disimpan",Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            //Toast.makeText(this,"Data Kosong",Toast.LENGTH_SHORT).show();
            if(txt_nama.getText().toString().equals("")){
                txt_nama.setHint("Nama Harap Diisi");
                txt_nama.setHintTextColor(Color.RED);
            }
            if(txt_alamat.getText().toString().equals("")){
                txt_alamat.setHint("Alamat Harap Diisi");
                txt_alamat.setHintTextColor(Color.RED);
            }
            if(txt_nope.getText().toString().equals("")){
                txt_nope.setHint("Telpon Harap Diisi");
                txt_nope.setHintTextColor(Color.RED);
            }
            if(txt_email.getText().toString().equals("")){
                txt_nama.setHint("Email Harap Diisi");
                txt_email.setHintTextColor(Color.RED);
            }
            if(txt_password.getText().toString().equals("")){
                txt_password.setHint("Password Harap Diisi");
                txt_password.setHintTextColor(Color.RED);
            }

        }
    }

    public void klik_login(View view) {
        Intent intent =new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }
}
