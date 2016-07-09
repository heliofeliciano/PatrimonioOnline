package br.com.patrimonioonline.domain.bem;

import android.content.Context;

import br.com.patrimonioonline.domain.models.entities.AquisicaoEntity;
import br.com.patrimonioonline.domain.models.entities.BemEntity;
import br.com.patrimonioonline.domain.models.entities.BemTipoDepreciacaoEntity;
import br.com.patrimonioonline.domain.models.entities.BemTipoEntity;
import br.com.patrimonioonline.domain.models.entities.ClassificacaoEntity;
import br.com.patrimonioonline.domain.models.entities.ConvenioEntity;
import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;
import br.com.patrimonioonline.domain.models.entities.SituacaoEntity;

/**
 * Created by helio on 20/06/16.
 */

public interface IBemInteractor {

    void Salvar(Context context,
                IBemPresenter listener,
                int id,
                String descricao,
                BemTipoEntity bemTipoEntity,
                BemTipoDepreciacaoEntity bemTipoDepreciacaoEntity,
                ClassificacaoEntity classificacaoEntity,
                AquisicaoEntity aquisicaoEntity,
                DepartamentoEntity departamentoEntity,
                ConvenioEntity convenioEntity,
                SituacaoEntity situacaoEntity,
                String numeroPlaca,
                Double valorAquisicao,
                Double valorResidual,
                String dataAquisicao);

    void SalvarNovo(Context context, IBemPresenter listener, BemEntity bemEntity);
    void SalvarEdicao(Context context, IBemPresenter listener, BemEntity bemEntity);
    void PopularListaSituacao(Context context, IBemPresenter listener);
    void PopularListaBemtipos(Context context, IBemPresenter listener);
    void PopularListaBemTipoDepreciacao(Context context, IBemPresenter listener);
    void PopularListaAquisicao(Context context, IBemPresenter listener);
    void PopularListaConvenio(Context context, IBemPresenter listener);
    void PopularListaClassificacao(Context context, IBemPresenter listener);
    void PopularListaTemNumeroTombo(Context context, IBemPresenter listener);

    void buscarBemEntity(int idBem, IBemPresenter listener);
}
