package br.com.patrimonioonline.domain.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import br.com.patrimonioonline.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by helio on 06/06/16.
 */

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_login)
    EditText _etLogin;

    @BindView(R.id.et_login_senha)
    EditText _etSenha;
    

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        ButterKnife.bind(this);
    }
}
