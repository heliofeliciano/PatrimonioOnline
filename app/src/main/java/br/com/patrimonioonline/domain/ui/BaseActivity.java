package br.com.patrimonioonline.domain.ui;

import android.support.v7.app.AppCompatActivity;

import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;
import br.com.patrimonioonline.domain.models.readonly.UsuarioReadonly;
import br.com.patrimonioonline.domain.padrao.BasePresenter;
import br.com.patrimonioonline.domain.padrao.IBaseView;

/**
 * Created by helio on 28/06/16.
 */

public class BaseActivity extends AppCompatActivity implements IBaseView {

    BasePresenter presenter;

    public BaseActivity() {
        presenter = new BasePresenter(this);
    }

    @Override
    public Boolean verificarSeUsuarioLogado() {
        return presenter.verificarSeUsuarioLogado();
    }

    @Override
    public Boolean verificarSincronizacao() {
        return presenter.verificarSincronizacao();
    }

    @Override
    public DepartamentoEntity getDepartamentoLogado() {
        return presenter.buscarDepartamentoLogado();
    }

    @Override
    public UsuarioReadonly getUsuarioLogado() {
        return presenter.getUsuarioLogado();
    }


}
