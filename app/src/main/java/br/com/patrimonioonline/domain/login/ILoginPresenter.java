package br.com.patrimonioonline.domain.login;

/**
 * Created by helio on 12/06/16.
 */

public interface ILoginPresenter {

    void realizarLogin(String usuario, String senha);
    void cadastrarRegIdDispositivo();
    void cadastrarRegIdDispositivoResult();
    //void onDepartamentosPorUsuario(List<DepartamentoEntity> lista);
    //void buscarDepartamentosPorUsuario();
    void sucessoRealizarLogin(String msg);
    void falhouRealizarLogin(String msg);
}
