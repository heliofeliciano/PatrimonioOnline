package br.com.patrimonioonline.domain.login;

/**
 * Created by helio on 08/06/16.
 */

public interface ILoginView {

    //void habilitarEscolhaDoDepartamento(List<DepartamentoEntity> departamentoEntities);
    void loginSucesso();
    void loginFalhou();
    void navegarParaProximaTela();

}
