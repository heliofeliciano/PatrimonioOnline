package br.com.patrimonioonline.domain.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.List;

import br.com.patrimonioonline.R;
import br.com.patrimonioonline.domain.bem.BemListaPresenter;
import br.com.patrimonioonline.domain.bem.IBemListaView;
import br.com.patrimonioonline.domain.models.entities.BemTipoEntity;
import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;
import br.com.patrimonioonline.domain.models.readonly.BemTipoReadonly;
import br.com.patrimonioonline.lib.GsonLib;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.BemTipoEntityRealmProxy;

/**
 * Created by helio on 18/06/16.
 */

public class BemListaActivity extends AppCompatActivity implements IBemListaView {

    @BindView(R.id.lvListaBens)
    ListView lvLista;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    BemListaPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_bens);

        ButterKnife.bind(this);

        if (toolbar != null)
        {
            setSupportActionBar(toolbar);
        }

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
                .title(R.string.str_escolher_departamento)
                .items(departamentoEntities)
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice(){
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        presenter.salvarEscolhaDepartamento(BemListaActivity.this, departamentoEntities.get(dialog.getSelectedIndex()));
                        return true;
                    }
                })
                .positiveText(R.string.str_escolher)
                .show();
    }

    @Override
    public void onExibirTiposBens(final List<BemTipoEntity> bemTipoEntities) {
        new MaterialDialog.Builder(this)
                .title(R.string.str_escolher_bem_tipo)
                .items(bemTipoEntities)
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice(){
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        irParaActivityAdicionarBem(bemTipoEntities.get(dialog.getSelectedIndex()));
                        return true;
                    }
                })
                .positiveText(R.string.str_escolher)
                .show();
    }

    @Override
    public void irParaActivityAdicionarBem(BemTipoEntity bemTipoEntity) {

        Intent _intent = new Intent(this, BemCadastrarActivity.class);
        //Bundle _bundle = new Bundle();

        BemTipoReadonly _bemTipo = new BemTipoReadonly();
        _bemTipo.id = ((BemTipoEntityRealmProxy) bemTipoEntity).realmGet$id();
        _bemTipo.descricao = ((BemTipoEntityRealmProxy) bemTipoEntity).realmGet$descricao();

        _intent.putExtra("BemTipoReadonly", GsonLib.converterObjetoParaJson(_bemTipo));

        startActivity(_intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_adicionar_bem:
                //startActivity(new Intent(this, BemCadastrarActivity.class));
                presenter.buscarBemTipos();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
