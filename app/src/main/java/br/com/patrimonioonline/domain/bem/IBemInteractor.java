package br.com.patrimonioonline.domain.bem;

import android.content.Context;

import br.com.patrimonioonline.domain.models.entities.BemEntity;

/**
 * Created by helio on 20/06/16.
 */

public interface IBemInteractor {

    void Salvar(Context context, IBemPresenter listener, BemEntity bemEntity);
    void PopularListaSituacao(Context context, IBemPresenter listener);
    void PopularListaBemtipos(Context context, IBemPresenter listener);
    void PopularListaBemTipoDepreciacao(Context context, IBemPresenter listener);
    void PopularListaAquisicao(Context context, IBemPresenter listener);
    void PopularListaConvenio(Context context, IBemPresenter listener);
    void PopularListaClassificacao(Context context, IBemPresenter listener);
}
