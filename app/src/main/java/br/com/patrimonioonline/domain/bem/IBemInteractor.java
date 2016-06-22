package br.com.patrimonioonline.domain.bem;

import android.content.Context;

import java.util.List;

import br.com.patrimonioonline.domain.models.entities.AquisicaoEntity;
import br.com.patrimonioonline.domain.models.entities.ClassificacaoEntity;
import br.com.patrimonioonline.domain.models.entities.ConvenioEntity;
import br.com.patrimonioonline.domain.models.entities.SituacaoEntity;

/**
 * Created by helio on 20/06/16.
 */

public interface IBemInteractor {

    void CadastrarBem(Context context, IBemPresenter listener);
    void PopularListaSituacao(Context context, IBemPresenter listener);
    void PopularListaBemtipos(Context context, IBemPresenter listener);
    void PopularListaAquisicao(Context context, IBemPresenter listener);
    void PopularListaConvenio(Context context, IBemPresenter listener);
    void PopularListaClassificacao(Context context, IBemPresenter listener);
}
