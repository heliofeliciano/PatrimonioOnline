package br.com.patrimonioonline.domain.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import br.com.patrimonioonline.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by helio on 18/06/16.
 */

public class BensListaActivity extends AppCompatActivity {

    @BindView(R.id.lv_lista_bens)
    ListView lvLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_bens);

        ButterKnife.bind(this);
    }
}
