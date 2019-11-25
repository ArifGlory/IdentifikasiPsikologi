package com.example.fujimiya.clientpakarpsikologi;

import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

import java.io.IOException;
import java.io.InputStream;

public class TranslateActivity extends AppCompatActivity {

    EditText etSource;
    Button btnTranslate;
    TextView txtHasil;
    Translate translate;
    private String originalText;
    private String translatedText;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);

        etSource = findViewById(R.id.etSource);
        btnTranslate = findViewById(R.id.btnTranslate);
        txtHasil = findViewById(R.id.txtHasil);

        getTranslateService();

        btnTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                translate();
            }
        });


    }

    public void translate() {

        //Get input text to be translated:
        originalText = etSource.getText().toString();
        Translation translation = translate.translate(originalText, Translate.TranslateOption.targetLanguage("en"), Translate.TranslateOption.model("base"));
        translatedText = translation.getTranslatedText();

        //Translated text and original text are set to TextViews:
        txtHasil.setText(translatedText);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void getTranslateService() {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try (InputStream is = getResources().openRawResource(R.raw.credentials)) {

            //Get credentials:
            final GoogleCredentials myCredentials = GoogleCredentials.fromStream(is);

            //Set credentials and get translate service:
            TranslateOptions translateOptions = TranslateOptions.newBuilder().setCredentials(myCredentials).build();
            translate = translateOptions.getService();

        } catch (IOException ioe) {
            ioe.printStackTrace();

        }
    }
}
