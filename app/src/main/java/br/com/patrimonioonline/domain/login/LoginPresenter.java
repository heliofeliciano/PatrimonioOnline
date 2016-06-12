package br.com.patrimonioonline.domain.login;

import br.com.patrimonioonline.domain.login.async.AsyncLoginInteractor;

/**
 * Created by helio on 12/06/16.
 */

public class LoginPresenter implements ILoginPresenter, OnLoginFinishedListener {

    private ILoginView view;
    private AsyncLoginInteractor interactor;

    public LoginPresenter(ILoginView loginView) {
        this.view = loginView;
        this.interactor = new AsyncLoginInteractor();
    }

    public void tentativaLogin(String usuario, String senha){
        interactor.validarLogin(this, usuario, senha);
    }

    @Override
    public void onError() {
        view.loginFalhou();
    }

    @Override
    public void onSuccess() {
        view.navigateToListActivity();
    }
}
