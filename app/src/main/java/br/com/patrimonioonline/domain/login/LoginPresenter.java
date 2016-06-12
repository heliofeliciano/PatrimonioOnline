package br.com.patrimonioonline.domain.login;

/**
 * Created by helio on 12/06/16.
 */

public class LoginPresenter {

    private ILoginView view;
    private SynchronousLoginInteractor interactor;

    public LoginPresenter(ILoginView loginView) {
        this.view = loginView;
        this.interactor = new SynchronousLoginInteractor();
    }

    public void tentativaLogin(String usuario, String senha){
        boolean seValido = interactor.validarLogin(usuario, senha);
        if (seValido) {
            // navegar para a lista
            view.navigateToListActivity();
        } else {
            // falha no login
            view.loginFalhou();
        }
    }
}
