package br.com.patrimonioonline.domain.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.Calendar;
import java.util.List;

import br.com.patrimonioonline.R;
import br.com.patrimonioonline.domain.adapter.SpinnerAdapter.AquisicaoAdapter;
import br.com.patrimonioonline.domain.bem.BemInteractor;
import br.com.patrimonioonline.domain.bem.IBemPresenter;
import br.com.patrimonioonline.domain.models.entities.AquisicaoEntity;
import br.com.patrimonioonline.domain.models.entities.BemTipoEntity;
import br.com.patrimonioonline.domain.models.entities.ClassificacaoEntity;
import br.com.patrimonioonline.domain.models.entities.ConvenioEntity;
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
    @BindView(R.id.et_dataaquisicao_bem)
    EditText et_dataaquisicao;

    @BindView(R.id.sp_aquisicao_bem)
    Spinner sp_aquisicao;

    @BindView(R.id.sp_bem_tipo)
    MaterialSpinner sp_bemtipo;

    @BindView(R.id.sp_situacao)
    MaterialSpinner sp_situacao;

    // Adapaters
    private AquisicaoAdapter adapterAquisicao;

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
        interactor.PopularListaSituacao(getApplicationContext(), this);
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
    public void CadastrarBem() {

    }

    @Override
    public void PopularListaSituacao(List<SituacaoEntity> lista) {
        sp_situacao.setItems(lista);
    }

    @Override
    public void PopularListaAquisicao(List<AquisicaoEntity> lista) {
        // ADAPTER
        adapterAquisicao = new AquisicaoAdapter(this, R.layout.spinner_item, lista);
        sp_aquisicao.setAdapter(adapterAquisicao);

    }

    @Override
    public void PopularListaBemtipos(List<BemTipoEntity> lista) {

        sp_bemtipo.setItems(lista);

        sp_bemtipo.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<BemTipoEntity>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, BemTipoEntity item) {
                Snackbar.make(view, "Clicked " + item.toString(), Snackbar.LENGTH_LONG).show();
            }

        });


    }

    @Override
    public void PopularListaConvenio(List<ConvenioEntity> lista) {

    }

    @Override
    public void PopularListaClassificacao(List<ClassificacaoEntity> lista) {

    }

    @Override
    public void onSuccess(String msg) {

    }

    @Override
    public void onFailure(String msg) {

    }
}
