package br.com.patrimonioonline.domain.bem;

import java.util.ArrayList;
import java.util.List;

import br.com.patrimonioonline.domain.models.entities.AquisicaoEntity;
import br.com.patrimonioonline.domain.models.entities.BemTipoDepreciacaoEntity;
import br.com.patrimonioonline.domain.models.entities.BemTipoEntity;
import br.com.patrimonioonline.domain.models.entities.ClassificacaoEntity;
import br.com.patrimonioonline.domain.models.entities.ConvenioEntity;
import br.com.patrimonioonline.domain.models.entities.SituacaoEntity;

/**
 * Created by helio on 20/06/16.
 */

public interface IBemPresenter {

    void Salvar();
    void Cancelar();
    void PopularListaSituacao(List<SituacaoEntity> lista);
    void PopularListaAquisicao(List<AquisicaoEntity> lista);
    void PopularListaBemtipos(List<BemTipoEntity> lista);
    void PopularListaBemTipoDepreciacao(List<BemTipoDepreciacaoEntity> lista);
    void PopularListaConvenio(List<ConvenioEntity> lista);
    void PopularListaClassificacao(List<ClassificacaoEntity> lista);
    void PopularListaTemNumeroTombo(ArrayList<String> lista);

    void SalvoComSucesso();
    void ErroAoSalvar();

}
