package br.com.patrimonioonline.domain.login;

/**
 * Created by helio on 12/06/16.
 */

public class SynchronousLoginInteractor implements ILoginInteractor {

    public SynchronousLoginInteractor() {
    }

    public boolean validarLogin(String usuario, String senha) {
        return true;
    }
}
