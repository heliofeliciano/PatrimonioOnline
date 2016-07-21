package br.com.patrimonioonline.domain.login.async;

import android.content.Context;

import br.com.patrimonioonline.domain.login.ILoginPresenter;

/**
 * Created by helio on 13/06/16.
 */

public interface ILoginAsyncInteractor {

    void validarLogin(Context context, ILoginPresenter listener, String usuario, String senha);
    void cadastrarRegIdDispositivo(Context context, ILoginPresenter listener);
    //void buscarDepartamentosDoUsuario(Context context, ILoginPresenter listener);

}
