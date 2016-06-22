package br.com.patrimonioonline.domain.bem;

import java.util.List;

import br.com.patrimonioonline.domain.IMainPresenter;
import br.com.patrimonioonline.domain.models.entities.AquisicaoEntity;
import br.com.patrimonioonline.domain.models.entities.BemTipoEntity;
import br.com.patrimonioonline.domain.models.entities.ClassificacaoEntity;
import br.com.patrimonioonline.domain.models.entities.ConvenioEntity;
import br.com.patrimonioonline.domain.models.entities.SituacaoEntity;

/**
 * Created by helio on 20/06/16.
 */

public interface IBemPresenter extends IMainPresenter {

    void CadastrarBem();
    void PopularListaSituacao(List<SituacaoEntity> lista);
    void PopularListaAquisicao(List<AquisicaoEntity> lista);
    void PopularListaBemtipos(List<BemTipoEntity> lista);
    void PopularListaConvenio(List<ConvenioEntity> lista);
    void PopularListaClassificacao(List<ClassificacaoEntity> lista);

    @Override
    void onSuccess(String msg);

    @Override
    void onFailure(String msg);
}
