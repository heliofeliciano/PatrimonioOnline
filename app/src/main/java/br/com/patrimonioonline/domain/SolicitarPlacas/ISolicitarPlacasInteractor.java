package br.com.patrimonioonline.domain.SolicitarPlacas;

import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;
import br.com.patrimonioonline.domain.models.entities.UsuarioEntity;
import br.com.patrimonioonline.domain.models.readonly.UsuarioReadonly;

/**
 * Created by helio on 20/07/16.
 */
public interface ISolicitarPlacasInteractor {

    void Salvar(ISolicitarPlacasPresenter listener,
                DepartamentoEntity departamentoEntity,
                UsuarioEntity usuarioEntity,
                int quantidadeSolicitada);

}
