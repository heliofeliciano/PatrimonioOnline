package br.com.patrimonioonline.domain.bem;

import br.com.patrimonioonline.domain.models.entities.BemEntity;

/**
 * Created by helio on 09/07/16.
 */

public interface IBemImagemPresenter {
    void onSalvarImagem();
    void onBuscarImagem(BemEntity bemEntity);
}
