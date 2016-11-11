package br.com.patrimonioonline.domain.bem;

import android.content.Context;

import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;

/**
 * Created by helio on 27/06/16.
 */

public interface IBemListaInteractor {

    void buscarBensPorDepartamento(IBemListaPresenter listener);
    void atualizarListaBens(IBemListaPresenter listener);
    void buscarBemTipo(Context context, IBemListaPresenter listener);
    void buscarDadosQrCode(IBemListaPresenter listener);

}
