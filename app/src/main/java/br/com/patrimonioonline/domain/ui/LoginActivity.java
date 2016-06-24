package br.com.patrimonioonline.domain.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;

import br.com.patrimonioonline.BuildConfig;
import br.com.patrimonioonline.R;
import br.com.patrimonioonline.domain.login.ILoginView;
import br.com.patrimonioonline.domain.login.LoginPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by helio on 06/06/16.
 */

public class LoginActivity extends AppCompatActivity implements ILoginView {

    @BindView(R.id.et_login)
    EditText _etLogin;

    @BindView(R.id.et_login_senha)
    EditText _etSenha;

    @BindView(R.id.sp_login_departamentos)
    MaterialSpinner _sp_login_departamentos;

    LoginPresenter presenter;
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
    }

    @OnClick(R.id.btn_login)
    public void btnLogin(View view){

        progressDialog = ProgressDialog.show(this, "Autenticando ...", null);

        String usuario = _etLogin.getText().toString();
        String senha = _etSenha.getText().toString();

        presenter.tentativaLogin(usuario, senha);

    }

    @Override
    public void habilitarEscolhaDoDepartamento() {
        progressDialog.dismiss();

        //_sp_login_departamentos.setVisibility(View.VISIBLE);
        startActivity(new Intent(this, SincronizacaoActivity.class));
        Toast.makeText(this, "Ok, proxima activity.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFalhou() {
        progressDialog.dismiss();
        Toast.makeText(this, "Login falhou. Tente novamente mais tarde.", Toast.LENGTH_SHORT).show();
    }
}