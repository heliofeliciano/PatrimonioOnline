package br.com.patrimonioonline.domain.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.List;

import br.com.patrimonioonline.R;
import br.com.patrimonioonline.domain.bem.BemListaPresenter;
import br.com.patrimonioonline.domain.bem.IBemListaView;
import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by helio on 18/06/16.
 */

public class BensListaActivity extends AppCompatActivity implements IBemListaView {

    @BindView(R.id.lvListaBens)
    ListView lvLista;

    BemListaPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_bens);

        ButterKnife.bind(this);

        presenter = new BemListaPresenter(getApplicationContext(), this);

        verificarSeSetorJaFoiEscolhido();
    }

    @Override
    public void setorEscolhido() {
        Toast.makeText(this, "O setor já foi escolhido", Toast.LENGTH_LONG).show();
    }

    @Override
    public void setorNaoEscolhido() {
        presenter.buscarDepartamentosPorUsuario();
    }

    @Override
    public void verificarSeSetorJaFoiEscolhido() {
        presenter.verificarSeSetorJaEscolhido();
    }

    @Override
    public void onBuscaDepartamentoSucesso(List<DepartamentoEntity> departamentoEntities) {
        Toast.makeText(this, "Foi retornado " + departamentoEntities.size() + " departamentos", Toast.LENGTH_SHORT).show();
        onExibirListaDepartamentos(departamentoEntities);
    }

    @Override
    public void onExibirListaDepartamentos(final List<DepartamentoEntity> departamentoEntities) {
        new MaterialDialog.Builder(this)
                .title("Escolher departamento")
                .items(departamentoEntities)
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice(){
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        /*Toast.makeText(BensListaActivity.this,
                                departamentoEntities.get(dialog.getSelectedIndex()).descricao,
                                Toast.LENGTH_SHORT).show();*/
                        presenter.salvarEscolhaDepartamento(BensListaActivity.this, departamentoEntities.get(dialog.getSelectedIndex()));

                        return true;
                    }
                })
                .positiveText(R.string.str_escolher)
                .show();
    }
}
