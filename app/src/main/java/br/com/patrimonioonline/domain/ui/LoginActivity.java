package br.com.patrimonioonline.domain.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

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

    LoginPresenter presenter;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        if (BuildConfig.DEBUG){
            _etLogin.setText("dbseller");
            _etSenha.setText("P@paiNoel");
        }

         presenter = new LoginPresenter(this);
    }

    @OnClick(R.id.btn_login_solicitaracesso)
    public void btnSolicitarAcesso(View view){

    }

    @Override
    public void navigateToListActivity() {

    }

    @Override
    public void loginFailed() {

    }
}