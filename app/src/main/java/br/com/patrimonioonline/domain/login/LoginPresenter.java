package br.com.patrimonioonline.domain.login;

import android.content.Context;

import java.util.List;

import br.com.patrimonioonline.domain.login.async.LoginAsyncInteractor;
import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;

/**
 * Created by helio on 12/06/16.
 */

public class LoginPresenter implements ILoginPresenter {

    private ILoginView view;
    private LoginAsyncInteractor interactor;
    private Context context;

    public LoginPresenter(Context context, ILoginView loginView) {
        this.view = loginView;
        this.interactor = new LoginAsyncInteractor();
        this.context = context;
    }

    public void tentativaLogin(String usuario, String senha) {
        interactor.validarLogin(context, this, usuario, senha);
    }

    @Override
    public void onDepartamentosPorUsuario(List<DepartamentoEntity> lista) {
        view.habilitarEscolhaDoDepartamento(lista);
    }

    @Override
    public void buscarDepartamentosPorUsuario() {
        interactor.buscarDepartamentosDoUsuario(context, this);
    }

    @Override
    public void onSuccess(String msg) {
        view.loginSucesso();
    }

    @Override
    public void onFailure(String msg) {
        view.loginFalhou();
    }
}
