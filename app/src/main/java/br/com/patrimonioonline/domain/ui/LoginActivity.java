package br.com.patrimonioonline.domain.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.patrimonioonline.BuildConfig;
import br.com.patrimonioonline.R;
import br.com.patrimonioonline.domain.consts.GoogleConst;
import br.com.patrimonioonline.domain.gcm_config.ConfigGcmInteractor;
import br.com.patrimonioonline.domain.gcm_config.IConfigGcm;
import br.com.patrimonioonline.domain.login.ILoginView;
import br.com.patrimonioonline.domain.login.LoginPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by helio on 06/06/16.
 */

public class LoginActivity extends BaseActivity implements ILoginView, IConfigGcm {

    @BindView(R.id.et_login)
    EditText _etLogin;

    @BindView(R.id.et_login_senha)
    EditText _etSenha;

    LoginPresenter presenter;
    ConfigGcmInteractor interactorGcm;
    ProgressDialog progressDialog;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        if (BuildConfig.DEBUG){
            _etLogin.setText("dbseller");
            _etSenha.setText("P@paiNoel");
        }

         presenter = new LoginPresenter(getApplicationContext(), this);
         interactorGcm = new ConfigGcmInteractor();
    }

    @OnClick(R.id.btn_login)
    public void btnLogin(View view){

        progressDialog = ProgressDialog.show(this, "Autenticando ...", null);

        String usuario = _etLogin.getText().toString();
        String senha = _etSenha.getText().toString();

        presenter.realizarLogin(usuario, senha);

    }

    @Override
    public void loginSucesso(String msg) {
        progressDialog.dismiss();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

        navegarParaProximaTela();
    }

    @Override
    public void loginFalhou(String msg) {
        progressDialog.dismiss();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navegarParaProximaTela() {

        // TODO: 11/9/16 Testes para utilizar o Realm em uma classe separada
        //finish();
        /*if (this.verificarSincronizacao()) {

            startActivity(new Intent(this, BemListaActivity.class));

        } else {

            startActivity(new Intent(this, SincronizacaoActivity.class));
        }*/

    }

    @Override
    public void cadastrarRegIdNoGoogle() {

        interactorGcm.salvarRegIdNoGoogle(this, getApplicationContext(), GoogleConst.GOOGLE_PROJ_ID);

    }

    @Override
    public void cadastrarRegIdNoGoogleResult(Boolean result, String msg) {

    }
}