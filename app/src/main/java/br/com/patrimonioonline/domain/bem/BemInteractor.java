package br.com.patrimonioonline.domain.bem;

import android.content.Context;

import br.com.patrimonioonline.domain.models.entities.AquisicaoEntity;
import br.com.patrimonioonline.domain.models.entities.BemEntity;
import br.com.patrimonioonline.domain.models.entities.BemTipoDepreciacaoEntity;
import br.com.patrimonioonline.domain.models.entities.BemTipoEntity;
import br.com.patrimonioonline.domain.models.entities.SituacaoEntity;
import br.com.patrimonioonline.domain.repos.Repository;

/**
 * Created by helio on 20/06/16.
 */

public class BemInteractor implements IBemInteractor {

    @Override
    public void Salvar(Context context, IBemPresenter listener, BemEntity bemEntity) {

        Repository<BemEntity> bemEntityRepository = new Repository<BemEntity>(BemEntity.class);
        bemEntityRepository.create(bemEntity);
        listener.SalvoComSucesso();
    }

    @Override
    public void PopularListaSituacao(Context context, IBemPresenter listener) {
        Repository<SituacaoEntity> situacaoEntityRepository = new Repository<>(SituacaoEntity.class);
        listener.PopularListaSituacao(situacaoEntityRepository.all());
    }

    @Override
    public void PopularListaBemtipos(Context context, IBemPresenter listener) {
        Repository<BemTipoEntity> bemTipoEntityRepository = new Repository<>(BemTipoEntity.class);
        listener.PopularListaBemtipos(bemTipoEntityRepository.all());
    }

    @Override
    public void PopularListaBemTipoDepreciacao(Context context, IBemPresenter listener) {
        Repository<BemTipoDepreciacaoEntity> bemTipoDepreciacaoEntityRepository = new Repository<>(BemTipoDepreciacaoEntity.class);
        listener.PopularListaBemTipoDepreciacao(bemTipoDepreciacaoEntityRepository.all());
    }

    @Override
    public void PopularListaAquisicao(Context context, IBemPresenter listener) {
        Repository<AquisicaoEntity> aquisicaoEntityRepository = new Repository<>(AquisicaoEntity.class);
        listener.PopularListaAquisicao(aquisicaoEntityRepository.all());
    }

    @Override
    public void PopularListaConvenio(Context context, IBemPresenter listener) {
    }

    @Override
    public void PopularListaClassificacao(Context context, IBemPresenter listener) {
    }
}
