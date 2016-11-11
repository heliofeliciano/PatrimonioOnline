package br.com.patrimonioonline.domain.padrao;

import android.content.Context;

import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;
import br.com.patrimonioonline.domain.models.entities.UsuarioEntity;

/**
 * Created by helio on 28/06/16.
 */

public class BasePresenter implements IBasePresenter {

    private BaseInteractor interactor;
    private Context context;
    private IBaseView view;

    public BasePresenter(Context context, IBaseView view) {
        interactor = new BaseInteractor(context);
        this.view = view;
    }

    @Override
    public Boolean verificarSeUsuarioLogado() {
        return interactor.verificarSeUsuarioLogado();
    }

    @Override
    public DepartamentoEntity buscarDepartamentoAtual() {
        return interactor.buscarDepartamentoAtual();
    }

    @Override
    public Boolean verificarSincronizacao() {
        return interactor.verificarSincronizacao();
    }

    @Override
    public UsuarioEntity getUsuarioLogado() {
        return interactor.getUsuarioLogado();
    }

    @Override
    public void logout() {
        interactor.logout(this);
    }

    @Override
    public void logoutSucesso() {
        view.logoutSucesso();
    }
}
