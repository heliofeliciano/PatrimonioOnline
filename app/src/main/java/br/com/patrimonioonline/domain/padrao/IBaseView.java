package br.com.patrimonioonline.domain.padrao;

import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;

/**
 * Created by helio on 28/06/16.
 */

public interface IBaseView {

    Boolean verificarSeUsuarioLogado();
    Boolean verificarSincronizacao();
    DepartamentoEntity getDepartamentoLogado();

}
