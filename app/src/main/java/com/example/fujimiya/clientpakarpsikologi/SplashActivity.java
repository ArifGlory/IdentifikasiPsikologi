package com.example.fujimiya.clientpakarpsikologi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.fujimiya.clientpakarpsikologi.Kelas.UserPreference;

public class SplashActivity extends AppCompatActivity {

    public UserPreference mUserPref;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mUserPref = new UserPreference(this);


        Thread timer = new Thread(){
            public void run (){

                if (mUserPref.getDisclaimer()){
                    i = new Intent(getApplicationContext(),LoginActivity.class);
                }else{
                    i = new Intent(getApplicationContext(),DisclaimerActivity.class);
                }

                try {
                    sleep(3000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }
}
