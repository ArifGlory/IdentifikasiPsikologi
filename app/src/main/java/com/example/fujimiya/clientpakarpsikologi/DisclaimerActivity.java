package com.example.fujimiya.clientpakarpsikologi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.fujimiya.clientpakarpsikologi.Kelas.UserPreference;

public class DisclaimerActivity extends AppCompatActivity {

    public UserPreference mUserPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);

        mUserPref = new UserPreference(this);
    }

    public void btn_masuk(View view) {
        mUserPref.setDisclaimer(true);

        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
    }
}
