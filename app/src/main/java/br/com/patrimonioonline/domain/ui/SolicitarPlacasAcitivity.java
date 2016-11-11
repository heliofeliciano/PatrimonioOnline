package br.com.patrimonioonline.domain.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import br.com.patrimonioonline.R;
import br.com.patrimonioonline.domain.SolicitarPlacas.ISolicitarPlacasPresenter;
import br.com.patrimonioonline.domain.SolicitarPlacas.SolicitarPlacasInteractor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by helio on 20/07/16.
 */

public class SolicitarPlacasAcitivity extends BaseActivity implements ISolicitarPlacasPresenter {

    @BindView(R.id.tvSolicitarPlaca_UsuarioSolicitante)
    TextView tvSolicitarPlaca_UsuarioSolicitante;

    @BindView(R.id.tvSolicitarPlacas_Departamento)
    TextView tvSolicitarPlaca_Departamento;

    @BindView(R.id.etSolicitarPlacas_Quantidade)
    TextView etSolicitarPlacas_Quantidade;

    SolicitarPlacasInteractor _interactor;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitar_placas);

        ButterKnife.bind(this);

        if (toolbar != null)
        {
            setSupportActionBar(toolbar);
        }

        init();
    }

    private void init() {

        getSupportActionBar().setTitle("Solicitar Placas");
        _interactor = new SolicitarPlacasInteractor();
        tvSolicitarPlaca_UsuarioSolicitante.setText(getUsuarioLogado().nome);
        tvSolicitarPlaca_Departamento.setText(String.valueOf(getDepartamentoAtual().getId()) + " - " + getDepartamentoAtual()
                .getDescricao());

    }

    @Override
    @OnClick(R.id.btnSolicitarPlacas_Salvar)
    public void Salvar(){

        if (validar()) {

            _interactor.Salvar(this,
                    getDepartamentoAtual(),
                    getUsuarioLogado(),
                    Integer.valueOf(etSolicitarPlacas_Quantidade.getText().toString()));

        }
    }

    // Verificar se todas as entradas estão ok para ser salvo em uma base de dados
    private Boolean validar() {

        int _quantidade = 0;

        if (!etSolicitarPlacas_Quantidade.getText().toString().isEmpty()) {
            _quantidade = Integer.valueOf(etSolicitarPlacas_Quantidade.getText().toString());
        } else {
            return false;
        }

        if (_quantidade <= 0) {
            return false;
        }

        return true;
    }

    @Override
    public void SalvarResult(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void Error(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btnSolicitarPlacas_Cancelar)
    @Override
    public void Cancelar() {
        finish();
    }
}
