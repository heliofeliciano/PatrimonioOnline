package br.com.patrimonioonline.domain.configuracao;

/**
 * Created by helio on 15/06/16.
 */

public interface ISincronizacaoView {

    void sincronizacaoOk();
    void sincronizacaoFalhou();
    void sincronizacaoOcorrendo();

}
