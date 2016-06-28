package br.com.patrimonioonline.domain.login;

import java.util.List;

import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;

/**
 * Created by helio on 08/06/16.
 */

public interface ILoginView {

    void habilitarEscolhaDoDepartamento(List<DepartamentoEntity> departamentoEntities);
    void loginSucesso();
    void loginFalhou();

}
