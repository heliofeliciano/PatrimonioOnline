package br.com.patrimonioonline.domain.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import br.com.patrimonioonline.R;
import br.com.patrimonioonline.domain.adapter.DepartamentoAdapter;
import br.com.patrimonioonline.domain.departamento.DepartamentoInteractor;
import br.com.patrimonioonline.domain.departamento.IDepartamentoPresenter;
import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by helio on 13/07/16.
 */

public class AlterarDepartamentoActivity extends BaseActivity implements IDepartamentoPresenter, AdapterView.OnItemClickListener {

    private DepartamentoEntity departamentoEntity;
    private DepartamentoEntity departamentoAtualEntity;
    private List<DepartamentoEntity> listaDepartamentos;
    private DepartamentoInteractor interactor;

    /*@BindView(R.id.spDepartamentos)
    MaterialSpinner spDepartamentos;*/

    @BindView(R.id.tv_departamento_atual)
    TextView tvDepartamentoAtual;

    @BindView(R.id.tv_departamento_escolhido)
    TextView tvDepartamentoEscolhido;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_departamento);

        ButterKnife.bind(this);

        if (toolbar != null)
        {
            setSupportActionBar(toolbar);
        }

        interactor = new DepartamentoInteractor();
        init();
    }

    private void init() {

        departamentoAtualEntity = getDepartamentoAtual();

        if (departamentoAtualEntity != null) {
            tvDepartamentoAtual.setText(
                    String.valueOf(departamentoAtualEntity.getId())
                            .concat(" - ")
                            .concat(departamentoAtualEntity.getDescricao())
            );

        }

        listarDepartamentos();
    }

    @Override
    public void listarDepartamentos() {
        interactor.ListarDepartamentos(this, this);
    }

    @Override
    public void listaDepartamentosSucesso(List<DepartamentoEntity> lista) {

        ListView lvDepartamentos = (ListView) findViewById(R.id.lvDepartamentos);
        ArrayAdapter adapter = new DepartamentoAdapter(this, R.layout.activity_alterar_departamento_item, lista);
        lvDepartamentos.setAdapter(adapter);
        lvDepartamentos.setOnItemClickListener(this);

    }

    @OnClick(R.id.btnAlterarDepartamentoSalvar)
    @Override
    public void AlterarDepartamento() {
        interactor.SalvarDepartamentoAtual(getApplicationContext(), departamentoEntity, this);
    }

    @Override
    public void AlterarDepartamentoResult() {
        showToast(getResources().getString(R.string.msgDepartamentoAlteradoComSucesso));
        navegarParaProximaActivity(new Intent(this, BemListaActivity.class));
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        departamentoEntity = (DepartamentoEntity) parent.getItemAtPosition(position);

        atualizarDepartamentoEscolhido();
    }

    public void atualizarDepartamentoEscolhido() {

        tvDepartamentoEscolhido.setText(departamentoEntity.toStringComCodigo());

    }

}
