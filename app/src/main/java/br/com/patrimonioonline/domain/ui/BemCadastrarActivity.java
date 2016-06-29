package br.com.patrimonioonline.domain.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;

import org.parceler.Parcels;

import java.util.Calendar;
import java.util.List;

import br.com.patrimonioonline.R;
import br.com.patrimonioonline.domain.bem.BemInteractor;
import br.com.patrimonioonline.domain.bem.IBemPresenter;
import br.com.patrimonioonline.domain.models.entities.AquisicaoEntity;
import br.com.patrimonioonline.domain.models.entities.BemTipoDepreciacaoEntity;
import br.com.patrimonioonline.domain.models.entities.BemTipoEntity;
import br.com.patrimonioonline.domain.models.entities.ClassificacaoEntity;
import br.com.patrimonioonline.domain.models.entities.ConvenioEntity;
import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;
import br.com.patrimonioonline.domain.models.entities.SituacaoEntity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by helio on 20/06/16.
 */

public class BemCadastrarActivity extends AppCompatActivity implements IBemPresenter {

    private BemInteractor interactor;

    // Views
    @BindView(R.id.etDescricaoBem)
    EditText etDescricaoBem;

    @BindView(R.id.et_dataaquisicao_bem)
    EditText et_dataaquisicao;

    @BindView(R.id.sp_aquisicao_bem)
    MaterialSpinner sp_aquisicao;

    @BindView(R.id.sp_bem_tipo)
    MaterialSpinner sp_bemtipo;

    @BindView(R.id.sp_situacao)
    MaterialSpinner sp_situacao;

    @BindView(R.id.sp_tipodepreciacao)
    MaterialSpinner sp_tipodepreciacao;

    // Adapaters
    // private AquisicaoAdapter adapterAquisicao;

    // Objetos
    BemTipoEntity bemTipoEntity;
    BemTipoDepreciacaoEntity bemTipoDepreciacaoEntity;
    ClassificacaoEntity classificacaoEntity;
    AquisicaoEntity aquisicaoEntity;
    DepartamentoEntity departamentoEntity;
    ConvenioEntity convenioEntity;
    SituacaoEntity situacaoEntity;

    private int dia, mes, ano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_bens);
        interactor = new BemInteractor();

        ButterKnife.bind(this);

        init();
    }

    private void init() {
        interactor.PopularListaAquisicao(getApplicationContext(), this);
        interactor.PopularListaBemtipos(getApplicationContext(), this);
        interactor.PopularListaBemTipoDepreciacao(getApplicationContext(), this);
        interactor.PopularListaSituacao(getApplicationContext(), this);

        // Buscar objetos passados como parametro para a Activity
        BemTipoEntity bemTipoEntity = Parcels.unwrap(getIntent().getParcelableExtra("BemTipoEntity"));
        Toast.makeText(this, bemTipoEntity.toString(), Toast.LENGTH_LONG).show();
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
                et_dataaquisicao.setText(new StringBuilder().append(dayOfMonth).append(" - ").append(monthOfYear-1).append(" - ").append(year).toString());

                ano = year;
                mes = (monthOfYear - 1);
                dia = dayOfMonth;
            }
        }, ano, mes, dia);

        datePickerDialog.show();
    }

    @Override
    @OnClick(R.id.btn_bem_salvar)
    public void Salvar() {

        /*

        bemEntity._id = 1;
        bemEntity.descricao = descricao;
        bemEntity.classificacaoEntity = classificacaoEntity;
        bemEntity.aquisicaoEntity = aquisicaoEntity;
        bemEntity.departamentoEntity = departamentoEntity;
        bemEntity.convenioEntity = convenioEntity;
        bemEntity.situacaoEntity = situacaoEntity;
        bemEntity.numeroPlaca = numeroPlaca;
        bemEntity.valorAquisicao = valorAquisicao;
        bemEntity.valorResidual = valorResidual;
        bemEntity.dataAquisicao = dataAquisicao;

        interactor.Salvar(
                getApplicationContext(),
                this,
        );*/

        String strSalvar = situacaoEntity.toString()
                 + "\n" + aquisicaoEntity.toString()
                 + "\n" + bemTipoEntity.toString()
                 + "\n" + bemTipoDepreciacaoEntity.toString()
                ;

        Toast.makeText(this, strSalvar, Toast.LENGTH_LONG).show();
    }

    @Override
    @OnClick(R.id.btn_bem_cancelar)
    public void Cancelar() {
        Toast.makeText(this, "Cancelar", Toast.LENGTH_LONG).show();
    }

    @Override
    public void PopularListaSituacao(List<SituacaoEntity> lista) {
        sp_situacao.setItems(lista);

        situacaoEntity = lista.get(sp_situacao.getSelectedIndex());

        sp_situacao.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<SituacaoEntity>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, SituacaoEntity item) {
                situacaoEntity = item;
            }
        });
    }

    @Override
    public void PopularListaAquisicao(List<AquisicaoEntity> lista) {

        sp_aquisicao.setItems(lista);

        aquisicaoEntity = lista.get(sp_aquisicao.getSelectedIndex());

        sp_aquisicao.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<AquisicaoEntity>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, AquisicaoEntity item) {
                aquisicaoEntity = item;
            }
        });
    }

    @Override
    public void PopularListaBemtipos(List<BemTipoEntity> lista) {

        sp_bemtipo.setItems(lista);

        bemTipoEntity = lista.get(sp_bemtipo.getSelectedIndex());

        sp_bemtipo.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<BemTipoEntity>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, BemTipoEntity item) {
                bemTipoEntity = item;
                Snackbar.make(view, item.toString() + " foi selecionado.", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void PopularListaBemTipoDepreciacao(List<BemTipoDepreciacaoEntity> lista) {
        sp_tipodepreciacao.setItems(lista);

        bemTipoDepreciacaoEntity = lista.get(sp_tipodepreciacao.getSelectedIndex());

        sp_tipodepreciacao.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<BemTipoDepreciacaoEntity>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, BemTipoDepreciacaoEntity item) {
                bemTipoDepreciacaoEntity = item;
            }
        });
    }

    @Override
    public void PopularListaConvenio(List<ConvenioEntity> lista) {
        convenioEntity = null;
    }

    @Override
    public void PopularListaClassificacao(List<ClassificacaoEntity> lista) {
        classificacaoEntity = null;
    }

    @Override
    public void SalvoComSucesso() {

    }

    @Override
    public void ErroAoSalvar() {

    }

}
