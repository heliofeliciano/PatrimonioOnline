package br.com.patrimonioonline.domain.configuracao;

/**
 * Created by helio on 15/06/16.
 */

public interface ISincronizacaoPresenter {

    void realizarSincronizacao();
    void onSuccess(String msg);
    void onFailure(String msg);
}
