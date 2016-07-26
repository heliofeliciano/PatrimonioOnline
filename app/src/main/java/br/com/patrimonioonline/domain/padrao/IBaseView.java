package br.com.patrimonioonline.domain.padrao;

import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;
import br.com.patrimonioonline.domain.models.readonly.UsuarioReadonly;

/**
 * Created by helio on 28/06/16.
 */

public interface IBaseView {

    Boolean verificarSeUsuarioLogado();
    Boolean verificarSincronizacao();
    DepartamentoEntity getDepartamentoLogado();
    UsuarioReadonly getUsuarioLogado();

}
