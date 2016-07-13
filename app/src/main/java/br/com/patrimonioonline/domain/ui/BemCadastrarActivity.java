package br.com.patrimonioonline.domain.ui;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.AlertDialogWrapper;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.jaredrummler.materialspinner.MaterialSpinnerAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.patrimonioonline.R;
import br.com.patrimonioonline.domain.bem.BemInteractor;
import br.com.patrimonioonline.domain.bem.IBemPresenter;
import br.com.patrimonioonline.domain.models.entities.AquisicaoEntity;
import br.com.patrimonioonline.domain.models.entities.BemEntity;
import br.com.patrimonioonline.domain.models.entities.BemTipoDepreciacaoEntity;
import br.com.patrimonioonline.domain.models.entities.BemTipoEntity;
import br.com.patrimonioonline.domain.models.entities.ClassificacaoEntity;
import br.com.patrimonioonline.domain.models.entities.ConvenioEntity;
import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;
import br.com.patrimonioonline.domain.models.entities.SituacaoEntity;
import br.com.patrimonioonline.lib.GsonLib;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by helio on 20/06/16.
 */

public class BemCadastrarActivity extends BaseActivity implements IBemPresenter {

    private BemInteractor interactor;

    // Views
    @BindView(R.id.etDescricaoBem)
    EditText etDescricaoBem;

    @BindView(R.id.et_numerotombo)
    EditText etNumeroTombo;

    @BindView(R.id.et_valoraquisicao_bem)
    EditText etValorAquisicao;

    @BindView(R.id.et_valorresidual_bem)
    EditText etValorResidual;

    /*@BindView(R.id.sp_temnumerotombo)
    MaterialSpinner spTemNumeroTombo;*/

    @BindView(R.id.et_dataaquisicao_bem)
    EditText etDataAquisicao;

    @BindView(R.id.sp_aquisicao_bem)
    MaterialSpinner spAquisicao;

    @BindView(R.id.sp_bem_tipo)
    MaterialSpinner sp_bemtipo;

    @BindView(R.id.sp_situacao)
    MaterialSpinner sp_situacao;

    @BindView(R.id.sp_tipodepreciacao)
    MaterialSpinner sp_tipodepreciacao;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    // Adapaters
    // private AquisicaoAdapter adapterAquisicao;

    // Objetos
    BemEntity _bemEntity;
    BemTipoEntity bemTipoEntity;
    BemTipoDepreciacaoEntity bemTipoDepreciacaoEntity;
    ClassificacaoEntity classificacaoEntity;
    AquisicaoEntity aquisicaoEntity;
    DepartamentoEntity departamentoEntity;
    ConvenioEntity convenioEntity;
    SituacaoEntity situacaoEntity;
    String temNumeroTombo;

    MaterialSpinnerAdapter<SituacaoEntity> adapterSituacao;
    List<BemTipoDepreciacaoEntity> listaBemTipoDepreciacao;
    List<SituacaoEntity> listaSituacao;
    List<AquisicaoEntity> listaAquisicao;
    List<BemTipoEntity> listaBemTipo;

    private int dia, mes, ano;

    private int idBem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bem_cadastrar);
        interactor = new BemInteractor();

        ButterKnife.bind(this);

        if (toolbar != null)
        {
            setSupportActionBar(toolbar);
        }

        init();
        checarVariaveisExtras();

    }

    private void checarVariaveisExtras() {

        String strIdBem = getIntent().getExtras().getString("IdBem");

        if (strIdBem == null) {
            initInsert();
        } else {
            idBem = Integer.valueOf(strIdBem);
            initEdit();
        }

    }

    private void init(){

        _bemEntity = new BemEntity();

        interactor.PopularListaAquisicao(getApplicationContext(), this);
        interactor.PopularListaBemtipos(getApplicationContext(), this);
        interactor.PopularListaBemTipoDepreciacao(getApplicationContext(), this);
        interactor.PopularListaSituacao(getApplicationContext(), this);
        //interactor.PopularListaTemNumeroTombo(getApplicationContext(), this);
    }

    private void initEdit() {

        interactor.buscarBemEntity(idBem, this);

    }

    private void initInsert() {
        // Buscar objetos passados como parametro para a Activity
        String strBemTipo = getIntent().getExtras().getString("BemTipo");
        bemTipoEntity = (BemTipoEntity) GsonLib.fromJsonObject(strBemTipo, new BemTipoEntity());
        /*getSupportActionBar().setTitle("Cadastro de " + bemTipoEntity.getDescricao());*/
    }

    @OnClick(R.id.et_dataaquisicao_bem)
    public void clicouDataAquisicao(){
        Calendar calendar = Calendar.getInstance();
        ano = calendar.get(Calendar.YEAR);
        mes = calendar.get(Calendar.MONTH);
        dia = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                etDataAquisicao.setText(new StringBuilder().append(dayOfMonth).append(" - ").append(monthOfYear-1).append(" - ").append(year).toString());

                ano = year;
                mes = (monthOfYear - 1);
                dia = dayOfMonth;
            }
        }, ano, mes, dia);

        datePickerDialog.show();
    }


    @OnClick(R.id.btn_bem_salvar)
    public void CheckInformacoesParaSalvar(){

        // checar informacoes para salvar
        Salvar();

    }

    @Override
    public void onSalvoNovo(BemEntity _entity) {
        Toast.makeText(this, "Bem salvo com sucesso", Toast.LENGTH_LONG).show();

        //if (bemTipoEntity.getDescricao().equals("MOVEIS")) {
            irParaActivityMapa(_entity);
        //} else {
        //    irParaActivityUploadImagens(_entity);
        //}
    }

    @Override
    public void onSalvoEdicao() {
        Toast.makeText(this, "Edicao", Toast.LENGTH_LONG).show();
    }

    @Override
    public void Salvar() {

        /*interactor.Salvar(getApplicationContext(),
                this,
                idBem,
                etDescricaoBem.getText().toString(),
                bemTipoEntity,
                bemTipoDepreciacaoEntity,
                classificacaoEntity,
                aquisicaoEntity,
                getDepartamentoLogado(),
                convenioEntity,
                situacaoEntity,
                etNumeroTombo.getText().toString(),
                Double.valueOf(etValorAquisicao.getText().toString()),
                Double.valueOf(etValorResidual.getText().toString()),
                etDataAquisicao.getText().toString());*/

        interactor.Salvar(getApplicationContext(),
                this,
                idBem,
                etDescricaoBem.getText().toString(),
                bemTipoEntity,
                bemTipoDepreciacaoEntity,
                classificacaoEntity,
                aquisicaoEntity,
                getDepartamentoLogado(),
                convenioEntity,
                situacaoEntity,
                etNumeroTombo.getText().toString(),
                Double.valueOf(etValorAquisicao.getText().toString()),
                Double.valueOf(0),
                etDataAquisicao.getText().toString());

    }

    @Override
    @OnClick(R.id.btn_bem_cancelar)
    public void Cancelar() {
        Toast.makeText(this, "Cancelar", Toast.LENGTH_LONG).show();
    }

    @Override
    public void EditarBemEntity(BemEntity bemEntity) {

        _bemEntity = bemEntity;

        // Inserir dados na view
        etDescricaoBem.setText(_bemEntity.getDescricao());
        etNumeroTombo.setText(_bemEntity.getNumeroPlaca());
        etDataAquisicao.setText(_bemEntity.getDataAquisicao());
        etValorAquisicao.setText(String.valueOf(_bemEntity.getValorAquisicao()));
        //etValorResidual.setText(String.valueOf(_bemEntity.getValorResidual()));
        etValorResidual.setText(String.valueOf(0));

        /*for (int i = 0; i <  listaBemTipoDepreciacao.size(); i++) {
            BemTipoDepreciacaoEntity _bemTipoDepreciacao = listaBemTipoDepreciacao.get(i);
            if (_bemTipoDepreciacao.getId() == _bemEntity.getBemTipoDepreciacaoEntity().getId()) {
                sp_tipodepreciacao.setSelectedIndex(i);
            }
        }*/

        for (int i = 0; i <  listaBemTipo.size(); i++) {
            BemTipoEntity _bemTipo = listaBemTipo.get(i);
            if (_bemTipo.getId() == _bemEntity.getBemTipoEntity().getId()) {
                sp_bemtipo.setSelectedIndex(i);
            }
        }

        for (int i = 0; i <  listaAquisicao.size(); i++) {
            AquisicaoEntity _aquisicao = listaAquisicao.get(i);
            if (_aquisicao.getId() == _bemEntity.getAquisicaoEntity().getId()) {
                spAquisicao.setSelectedIndex(i);
            }
        }

        for (int i = 0; i <  listaSituacao.size(); i++) {
            SituacaoEntity _situacao = listaSituacao.get(i);
            if (_situacao.getId() == _bemEntity.getSituacaoEntity().getId()) {
                sp_situacao.setSelectedIndex(i);
            }
        }

    }

    @Override
    public void PopularListaSituacao(List<SituacaoEntity> lista) {
        listaSituacao = new ArrayList<SituacaoEntity>();
        listaSituacao = lista;

        sp_situacao.setItems(lista);

        situacaoEntity = (lista.get(sp_situacao.getSelectedIndex()));

        sp_situacao.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<SituacaoEntity>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, SituacaoEntity item) {
                _bemEntity.setSituacaoEntity(item);
                //situacaoEntity = item;
            }


        });
    }

    @Override
    public void PopularListaAquisicao(List<AquisicaoEntity> lista) {
        listaAquisicao = new ArrayList<AquisicaoEntity>();
        listaAquisicao = lista;

        spAquisicao.setItems(lista);

        aquisicaoEntity = (lista.get(spAquisicao.getSelectedIndex()));

        spAquisicao.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<AquisicaoEntity>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, AquisicaoEntity item) {
                _bemEntity.setAquisicaoEntity(item);
                //aquisicaoEntity = item;
            }
        });
    }

    @Override
    public void PopularListaBemtipos(List<BemTipoEntity> lista) {
        listaBemTipo = new ArrayList<BemTipoEntity>();
        listaBemTipo = lista;

        sp_bemtipo.setItems(lista);

        bemTipoEntity = (lista.get(sp_bemtipo.getSelectedIndex()));

        sp_bemtipo.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<BemTipoEntity>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, BemTipoEntity item) {
                _bemEntity.setBemTipoEntity(item);
                //bemTipoEntity = item;
                Snackbar.make(view, item.toString() + " foi selecionado.", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void PopularListaBemTipoDepreciacao(List<BemTipoDepreciacaoEntity> lista) {

        _bemEntity.setBemTipoDepreciacaoEntity(null);

        /*listaBemTipoDepreciacao = new ArrayList<BemTipoDepreciacaoEntity>();
        listaBemTipoDepreciacao = lista;

        sp_tipodepreciacao.setItems(lista);

        bemTipoDepreciacaoEntity = (lista.get(sp_tipodepreciacao.getSelectedIndex()));

        sp_tipodepreciacao.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<BemTipoDepreciacaoEntity>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, BemTipoDepreciacaoEntity item) {
                _bemEntity.setBemTipoDepreciacaoEntity(item);
                //bemTipoDepreciacaoEntity = item;
            }
        });*/
    }

    @Override
    public void PopularListaConvenio(List<ConvenioEntity> lista) {
        _bemEntity.setConvenioEntity(null);
        //convenioEntity = null;
    }

    @Override
    public void PopularListaClassificacao(List<ClassificacaoEntity> lista) {
        _bemEntity.setClassificacaoEntity(null);
        //classificacaoEntity = null;
    }

    @Override
    public void PopularListaTemNumeroTombo(ArrayList<String> lista) {
        /*spTemNumeroTombo.setItems(lista);

        temNumeroTombo = lista.get(spTemNumeroTombo.getSelectedIndex());

        spTemNumeroTombo.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                temNumeroTombo = item;
                etNumeroTombo.setVisibility(temNumeroTombo.equals("Sim") ? View.VISIBLE : View.INVISIBLE);
            }
        });*/
    }

    @Override
    public void irParaActivityUploadImagens(BemEntity _entity) {

        Intent _intent = new Intent(this, BemCadastrarImagensActivity.class);
        _intent.putExtra("IdBem", String.valueOf(_entity.getId()));

        startActivity(_intent);
    }

    @Override
    public void irParaActivityMapa(BemEntity _entity) {

        Intent _intent = new Intent(this, MapsActivity.class);
        startActivity(_intent);

    }

    @Override
    public void ErroAoSalvar() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bem_cadastrar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_excluir_bem:
                new AlertDialogWrapper.Builder(this)
                        .setTitle(R.string.pergunta_excluir_bem)
                        .setMessage(R.string.pergunta_excluir_bem_msg)
                        .setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                interactor.deletarBem(idBem, BemCadastrarActivity.this);
                            }
                        })
                        .show();

                return true;
            case R.id.action_add_imagem_bem:
                Intent it = new Intent(this, BemCadastrarImagensActivity.class);
                it.putExtra("IdBem", String.valueOf(idBem));
                startActivity(it);
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onDeletarBem() {
        Toast.makeText(this, "Bem excluído com sucesso", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, BemListaActivity.class));
        finish();
    }
}
