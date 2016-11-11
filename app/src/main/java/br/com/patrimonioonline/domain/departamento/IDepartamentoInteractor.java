package br.com.patrimonioonline.domain.departamento;

import android.content.Context;

import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;

/**
 * Created by helio on 13/07/16.
 */
public interface IDepartamentoInteractor {

    void SalvarDepartamentoAtual(Context context, DepartamentoEntity departamentoEntity, IDepartamentoPresenter listener);
    void ListarDepartamentos(Context context, IDepartamentoPresenter listener);
}
