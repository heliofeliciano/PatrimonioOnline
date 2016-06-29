package br.com.patrimonioonline.domain.ui;

import android.content.Intent;
import android.os.Bundle;

import br.com.patrimonioonline.domain.splashscreen.ISplashScreenView;

public class SplashScreenActivity extends BaseActivity implements ISplashScreenView {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (verificarSeUsuarioLogado()) {
            this.verificacaoInicialSucesso();
        } else {
            this.verificacaoInicialFalhou();
        }
    }


    @Override
    public void verificacaoInicialSucesso() {
        intent = new Intent(this, BemListaActivity.class);
        //intent = new Intent(this, SincronizacaoActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void verificacaoInicialFalhou() {
        intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
