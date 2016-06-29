package br.com.patrimonioonline.domain.padrao;

import android.content.Context;

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
    public Boolean verificarSincronizacao() {
        return true;
    }
}