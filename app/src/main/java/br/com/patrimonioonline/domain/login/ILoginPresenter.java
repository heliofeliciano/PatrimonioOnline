package br.com.patrimonioonline.domain.login;

import java.util.List;

import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;

/**
 * Created by helio on 12/06/16.
 */

public interface ILoginPresenter {

    void tentativaLogin(String usuario, String senha);
    void onDepartamentosPorUsuario(List<DepartamentoEntity> lista);
    void buscarDepartamentosPorUsuario();
    void onSuccess(String msg);
    void onFailure(String msg);
}
