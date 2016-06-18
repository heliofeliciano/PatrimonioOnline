package br.com.patrimonioonline.domain.login;

import android.content.Context;

import br.com.patrimonioonline.domain.IMainPresenter;

/**
 * Created by helio on 12/06/16.
 */

public interface ILoginPresenter extends IMainPresenter {

    void tentativaLogin(String usuario, String senha);

    @Override
    void onSuccess(String msg);

    @Override
    void onFailure(String msg);
}
