package br.com.patrimonioonline.domain.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.List;

import br.com.patrimonioonline.R;
import br.com.patrimonioonline.domain.adapter.RealmRecyclerViewBemListaAdapter;
import br.com.patrimonioonline.domain.bem.BemListaPresenter;
import br.com.patrimonioonline.domain.bem.IBemListaView;
import br.com.patrimonioonline.domain.models.entities.BemEntity;
import br.com.patrimonioonline.domain.models.entities.BemTipoEntity;
import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import io.realm.RealmResults;

/**
 * Created by helio on 18/06/16.
 */

public class BemListaActivity extends BaseActivity implements IBemListaView {

    /*@BindView(R.id.lvListaBens)
    ListView lvLista;*/

    @BindView(R.id.rvRealmBemLista)
    RealmRecyclerView rvRealmBemLista;

    RealmRecyclerViewBemListaAdapter adapter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    BemListaPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bem_lista_recycler);

        ButterKnife.bind(this);

        if (toolbar != null)
        {
            setSupportActionBar(toolbar);
        }

        //init();
    }

    @Override
    protected void onResume() {
        super.onResume();

        init();
    }

    @Override
    public void onListaBensPorDepartamento(RealmResults<BemEntity> lista) {

        adapter = new RealmRecyclerViewBemListaAdapter(this, lista, true, true);
        rvRealmBemLista.setAdapter(adapter);

        // Atualizar lista
        rvRealmBemLista.setOnRefreshListener(new RealmRecyclerView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.atualizarListaBens();
            }
        });
    }

    @Override
    public void onAtualizarListaBens(RealmResults<BemEntity> lista) {

        rvRealmBemLista.setRefreshing(false);

    }

    @Override
    public void onListaBensVazia() {

        Toast.makeText(this, "Não há bens cadastrados. Clique no + para adicionar um bem", Toast.LENGTH_SHORT).show();

    }

    private void init() {
        presenter = new BemListaPresenter(getApplicationContext(), this);
        verificarSeSetorJaFoiEscolhido();
        presenter.buscarListaBens();
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
        _intent.putExtra("BemTipo", bemTipoEntity.converterParaJson());

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
            case R.id.action_alterar_departamento:
                startActivity(new Intent(this, AlterarDepartamentoActivity.class));
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
