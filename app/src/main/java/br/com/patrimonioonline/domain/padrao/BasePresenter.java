package br.com.patrimonioonline.domain.padrao;

import android.content.Context;

import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;
import br.com.patrimonioonline.domain.models.readonly.UsuarioReadonly;

/**
 * Created by helio on 28/06/16.
 */

public class BasePresenter implements IBasePresenter {

    private BaseInteractor interactor;
    private Context context;

    public BasePresenter(Context context) {
        interactor = new BaseInteractor(context);
    }

    @Override
    public Boolean verificarSeUsuarioLogado() {
        return interactor.verificarSeUsuarioLogado();
    }

    @Override
    public DepartamentoEntity buscarDepartamentoLogado() {
        return interactor.buscarDepartamentoLogado();
    }

    @Override
    public Boolean verificarSincronizacao() {
        return interactor.verificarSincronizacao();
    }

    @Override
    public UsuarioReadonly getUsuarioLogado() {
        return interactor.getUsuarioLogado();
    }

    @Override
    public Boolean logout() {
        return interactor.logout();
    }
}
