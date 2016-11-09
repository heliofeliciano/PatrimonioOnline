package br.com.patrimonioonline.domain.login;

import android.content.Context;

import br.com.patrimonioonline.domain.login.async.LoginAsyncInteractor;

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

    @Override
    public void cadastrarRegIdDispositivo() {
        interactor.cadastrarRegIdDispositivo(context, this);
    }

    @Override
    public void cadastrarRegIdDispositivoResult() {
        view.navegarParaProximaTela();
    }

    public void realizarLogin(String usuario, String senha) {
        interactor.realizarLogin(context, this, usuario, senha);
    }

    @Override
    public void sucessoRealizarLogin(String msg) {
        view.loginSucesso(msg);
    }

    @Override
    public void falhouRealizarLogin(String msg) {
        view.loginFalhou(msg);
    }
}
