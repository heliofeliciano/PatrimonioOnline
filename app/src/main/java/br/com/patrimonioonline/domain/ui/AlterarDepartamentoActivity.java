package br.com.patrimonioonline.domain.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
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

public class AlterarDepartamentoActivity extends BaseActivity implements IDepartamentoPresenter {

    private DepartamentoEntity departamentoEntity;
    private DepartamentoEntity departamentoAtualEntity;
    private List<DepartamentoEntity> listaDepartamentos;
    private DepartamentoInteractor interactor;

    /*@BindView(R.id.spDepartamentos)
    MaterialSpinner spDepartamentos;*/

    @BindView(R.id.tvDepartamentoAtual)
    TextView tvDepartamentoAtual;

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

        departamentoAtualEntity = getDepartamentoLogado();

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

        /*listaDepartamentos = new ArrayList<DepartamentoEntity>();
        listaDepartamentos = lista;

        spDepartamentos.setItems(lista);

        departamentoEntity = (lista.get(spDepartamentos.getSelectedIndex()));

        spDepartamentos.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<DepartamentoEntity>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, DepartamentoEntity item) {
                departamentoEntity = item;
            }

        });*/

        // TODO: 11/9/16 Desenvolver um onclick do listview e popular a variavel departamentoEntity
        ListView lvDepartamentos = (ListView) findViewById(R.id.lvDepartamentos);
        ArrayAdapter adapter = new DepartamentoAdapter(this, R.layout.activity_alterar_departamento_item, lista);
        lvDepartamentos.setAdapter(adapter);

    }

    @OnClick(R.id.btnAlterarDepartamentoSalvar)
    @Override
    public void AlterarDepartamento() {
        interactor.Salvar(getApplicationContext(), departamentoEntity, this);
    }

    @Override
    public void AlterarDepartamentoResult() {
        Toast.makeText(this, "Departamento alterado com sucesso", Toast.LENGTH_SHORT).show();
        finish();
    }

}
