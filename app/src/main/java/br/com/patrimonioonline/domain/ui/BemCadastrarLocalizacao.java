package br.com.patrimonioonline.domain.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import br.com.patrimonioonline.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by helio on 11/07/16.
 */

public class BemCadastrarLocalizacao extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bem_mapa);

        ButterKnife.bind(this);

        if (toolbar != null)
        {
            setSupportActionBar(toolbar);
        }
    }
}
