package br.com.patrimonioonline.domain.bem;

import android.content.Context;

import java.util.List;

import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;

/**
 * Created by helio on 27/06/16.
 */

public interface IBemListaPresenter {

    // View
    void onBuscarDepartamentosPorUsuario(List<DepartamentoEntity> departamentoEntities);
    void setorEscolhido();
    void setorNaoEscolhido();

    // Interactor
    void verificarSeSetorJaEscolhido();
    void buscarDepartamentosPorUsuario();
    void salvarEscolhaDepartamento(Context context, DepartamentoEntity departamentoEntity);


}
