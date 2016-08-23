package br.com.patrimonioonline.domain.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import br.com.patrimonioonline.R;
import br.com.patrimonioonline.domain.configuracao.ISincronizacaoView;
import br.com.patrimonioonline.domain.configuracao.SincronizacaoPresenter;
import br.com.patrimonioonline.lib_ui.ActivityBase;

/**
 * Created by helio on 15/06/16.
 */

public class SincronizacaoActivity extends ActivityBase implements ISincronizacaoView {

    private SincronizacaoPresenter presenter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sincronizacao);

        presenter = new SincronizacaoPresenter(getApplicationContext(), this);
        sincronizar();
    }

    @Override
    public void sincronizacaoOk() {
        progressDialog.dismiss();
        this.showToast("Configuração finalizada com sucesso.");
        startActivity(new Intent(this, BemListaActivity.class));
    }

    @Override
    public void sincronizacaoFalhou() {
        progressDialog.dismiss();
    }

    @Override
    public void sincronizacaoOcorrendo() {

    }

    public void sincronizar(){
        progressDialog = ProgressDialog.show(this, "Sincronizando configurações iniciais ...", null);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.realizarSincronizacao();
            }
        }, 3000);

    }
}
