package br.com.patrimonioonline.domain.departamento;

import java.util.List;

import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;

/**
 * Created by helio on 13/07/16.
 */

public interface IDepartamentoPresenter {

    void AlterarDepartamento();
    void AlterarDepartamentoResult();

    void ListarDepartamentos();
    void ListarDepartamentosResult(List<DepartamentoEntity> lista);

}
