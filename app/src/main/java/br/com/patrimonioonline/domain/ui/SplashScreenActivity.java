package br.com.patrimonioonline.domain.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("LOG", "Open SplashScreen");

        //Intent intent = new Intent(this, LoginActivity.class);
        Intent intent = new Intent(this, SincronizacaoActivity.class);
        startActivity(intent);
        finish();
    }
}
