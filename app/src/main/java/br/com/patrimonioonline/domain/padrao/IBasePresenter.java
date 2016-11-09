package br.com.patrimonioonline.domain.padrao;

import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;
import br.com.patrimonioonline.domain.models.entities.UsuarioEntity;
import br.com.patrimonioonline.domain.models.readonly.UsuarioReadonly;

/**
 * Created by helio on 28/06/16.
 */

public interface IBasePresenter {

    Boolean verificarSeUsuarioLogado();
    Boolean verificarSincronizacao();
    DepartamentoEntity buscarDepartamentoLogado();
    UsuarioEntity getUsuarioLogado();
    void logout();
    void logoutSucesso();

}
