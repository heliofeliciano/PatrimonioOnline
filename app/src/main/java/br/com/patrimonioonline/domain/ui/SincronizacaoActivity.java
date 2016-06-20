package br.com.patrimonioonline.domain.ui;

import android.app.ProgressDialog;
import android.os.Bundle;

import br.com.patrimonioonline.R;
import br.com.patrimonioonline.domain.configuracao.ISincronizacaoView;
import br.com.patrimonioonline.domain.configuracao.SincronizacaoPresenter;
import br.com.patrimonioonline.lib_ui.ActivityBase;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by helio on 15/06/16.
 */

public class SincronizacaoActivity extends ActivityBase implements ISincronizacaoView {

    protected final String TAG = getClass().getSimpleName();
    private SincronizacaoPresenter presenter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sincronizacao);
        ButterKnife.bind(this);

        presenter = new SincronizacaoPresenter(getApplicationContext(), this);
    }

    @Override
    public void sincronizacaoOk() {
        progressDialog.dismiss();
        this.showToast("Configuração finalizada com sucesso.");

    }

    @Override
    public void sincronizacaoFalhou() {
        progressDialog.dismiss();
    }

    @Override
    public void sincronizacaoOcorrendo() {

    }

    @OnClick(R.id.btn_sincronizacao)
    public void sincronizarClick(){
        progressDialog = ProgressDialog.show(this, "Sincronizando configurações iniciais ...", null);
        presenter.realizarSincronizacao();
    }
}
