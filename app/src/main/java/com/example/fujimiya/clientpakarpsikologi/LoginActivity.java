package com.example.fujimiya.clientpakarpsikologi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private String username;
    private String password;
    private String id;
    private String nama;

    EditText txt_username,txt_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txt_username = findViewById(R.id.txt_username);
        txt_password = findViewById(R.id.txt_password);

        SharedPreferences sp=getSharedPreferences("login", Context.MODE_PRIVATE);
        String value = sp.getString("id_user", "");
        if(!value.equals("")){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }

    public void btn_masuk(View view) {
        final ProgressDialog loading;
        loading = ProgressDialog.show(LoginActivity.this,"Mengambil Data","Mohon Tunggu...",false,false);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mykateg = database.getReference("user_psikolog");
        mykateg.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(final DataSnapshot child : dataSnapshot.getChildren()){
                    //Toast.makeText(LoginActivity.this,""+child.child("nama").getValue().toString(),Toast.LENGTH_SHORT).show();

                    if(child.child("email").getValue().toString().equals(txt_username.getText().toString()) && child.child("password").getValue().toString().equals(txt_password.getText().toString())){
                        Toast.makeText(LoginActivity.this,"Berhasil...",Toast.LENGTH_SHORT).show();
                        loading.dismiss();

                        SharedPreferences sp=getSharedPreferences("login", Context.MODE_PRIVATE);
                        SharedPreferences.Editor ed=sp.edit();
                        ed.putString("id_user", child.child("id_user").getValue().toString());
                        ed.putString("nama", child.child("nama").getValue().toString());
                        ed.putString("email", child.child("email").getValue().toString());
                        ed.commit();

                        finish();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void daftarkuy(View view) {
        startActivity(new Intent(LoginActivity.this,DaftarActivity.class));

    }
}
