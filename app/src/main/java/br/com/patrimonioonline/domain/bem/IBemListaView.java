package br.com.patrimonioonline.domain.bem;

import java.util.List;

import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;

/**
 * Created by helio on 27/06/16.
 */

public interface IBemListaView {

    // View
    void onBuscaDepartamentoSucesso(List<DepartamentoEntity> departamentoEntities);
    void setorEscolhido();
    void setorNaoEscolhido();
    void onExibirListaDepartamentos(List<DepartamentoEntity> departamentoEntities);

    // Interactor
    void verificarSeSetorJaFoiEscolhido();



}
