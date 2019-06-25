package com.example.fujimiya.clientpakarpsikologi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class TentangActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_tentang);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        TextView textView = findViewById(R.id.tentang);
       /* textView.setText("Pakar Identifikasi Kesejahteraan Psikologis adalah aplikasi yang dapat mengidentifikasi anda dalam menentukan kesejahteraan pskologis yang anda miliki.\n" +
                "\n" +
                "Oleh :\n" +
                "Reny Rosa E 1417051120\n" +
                "Jurusan Ilmu Komputer 2018\n" +
                "\n" +
                "Konstributor :\n" +
                "Dosen Pembimbing 1 : Aristoteles, S.Si., M.Si.\n" +
                "Dosen Pembimbing 2 : Moch Johan Pratama, M.Psi.,.\n" +
                "Dosen Pembahas        : Admi Syarif, Dr.Eng.");*/
    }
}
