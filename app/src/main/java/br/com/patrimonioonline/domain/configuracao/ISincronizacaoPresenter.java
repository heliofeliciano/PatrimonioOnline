package br.com.patrimonioonline.domain.configuracao;

import br.com.patrimonioonline.domain.IMainPresenter;

/**
 * Created by helio on 15/06/16.
 */

public interface ISincronizacaoPresenter extends IMainPresenter {

    void realizarSincronizacao();

    @Override
    void onSuccess(String msg);

    @Override
    void onFailure(String msg);
}
