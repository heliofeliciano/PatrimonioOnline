package br.com.patrimonioonline.domain.login.async;

import br.com.patrimonioonline.domain.login.OnLoginFinishedListener;

/**
 * Created by helio on 12/06/16.
 */

public interface IAsyncLoginInteractor {

    void validarLogin(OnLoginFinishedListener listener, String usuario, String senha);
}
