package br.com.patrimonioonline.domain.configuracao;

import android.content.Context;

/**
 * Created by helio on 15/06/16.
 */

public class SincronizacaoPresenter implements ISincronizacaoPresenter {

    private ISincronizacaoView view;
    private SincronizacaoInteractor interactor;
    private Context context;

    public SincronizacaoPresenter(Context context, ISincronizacaoView sincronizacaoView ) {
        this.view = sincronizacaoView;
        this.interactor = new SincronizacaoInteractor();
        this.context = context;
    }

    @Override
    public void realizarSincronizacao() {
        interactor.realizarSincronizacao(context, this);
    }

    @Override
    public void onSuccess(String msg) {
        view.sincronizacaoOk();
    }

    @Override
    public void onFailure(String msg) {
        view.sincronizacaoFalhou();
    }

}
