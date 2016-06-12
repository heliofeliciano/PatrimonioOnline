package br.com.patrimonioonline.domain.login;

/**
 * Created by helio on 12/06/16.
 */

public class LoginPresenter {

    private ILoginView view;

    public LoginPresenter(ILoginView loginView) {
        this.view = loginView;
    }
}
