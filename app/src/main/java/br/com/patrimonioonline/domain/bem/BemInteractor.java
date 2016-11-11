package br.com.patrimonioonline.domain.bem;

import android.content.Context;

import java.util.ArrayList;

import br.com.patrimonioonline.domain.models.entities.AquisicaoEntity;
import br.com.patrimonioonline.domain.models.entities.BemEntity;
import br.com.patrimonioonline.domain.models.entities.BemTipoDepreciacaoEntity;
import br.com.patrimonioonline.domain.models.entities.BemTipoEntity;
import br.com.patrimonioonline.domain.models.entities.ClassificacaoEntity;
import br.com.patrimonioonline.domain.models.entities.ConvenioEntity;
import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;
import br.com.patrimonioonline.domain.models.entities.SituacaoEntity;
import br.com.patrimonioonline.domain.repos.Repository;

/**
 * Created by helio on 20/06/16.
 */

public class BemInteractor implements IBemInteractor {

    /*@Override
    public void SalvarDepartamentoAtual(Context context, IBemPresenter listener, BemEntity bemEntity) {

        Repository<BemEntity> bemEntityRepository = new Repository<BemEntity>(BemEntity.class);
        bemEntity.setId(bemEntityRepository.getProximoId());
        bemEntityRepository.create(bemEntity);
        listener.irParaActivityUploadImagens();
    }*/

    @Override
    public void Salvar(Context context,
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
                       String dataAquisicao) {

        BemEntity _bemEntity = new BemEntity();
        _bemEntity.setId(id);
        _bemEntity.setDescricao(descricao);
        _bemEntity.setBemTipoEntity(bemTipoEntity);
        _bemEntity.setBemTipoDepreciacaoEntity(bemTipoDepreciacaoEntity);
        _bemEntity.setClassificacaoEntity(classificacaoEntity);
        _bemEntity.setAquisicaoEntity(aquisicaoEntity);
        _bemEntity.setDepartamentoEntity(departamentoEntity);
        _bemEntity.setConvenioEntity(convenioEntity);
        _bemEntity.setSituacaoEntity(situacaoEntity);
        _bemEntity.setNumeroPlaca(numeroPlaca);
        _bemEntity.setValorAquisicao(valorAquisicao);
        _bemEntity.setValorResidual(valorResidual);
        _bemEntity.setDataAquisicao(dataAquisicao);

        if (id != 0) {
            SalvarEdicao(context, listener, _bemEntity);
        } else {
            SalvarNovo(context, listener, _bemEntity);
        }

    }

    @Override
    public void SalvarNovo(Context context, IBemPresenter listener, BemEntity bemEntity) {

        Repository<BemEntity> bemEntityRepository = new Repository<BemEntity>(BemEntity.class);
        bemEntity.setId(bemEntityRepository.getProximoId());
        bemEntityRepository.createOrUpdate(bemEntity);

        listener.onSalvoNovo(bemEntity);
    }

    @Override
    public void SalvarEdicao(Context context, IBemPresenter listener, BemEntity bemEntity) {

        Repository<BemEntity> bemEntityRepository = new Repository<BemEntity>(BemEntity.class);
        bemEntityRepository.createOrUpdate(bemEntity);

        listener.onSalvoEdicao();
    }

    @Override
    public void deletarBem(int idBem, IBemPresenter listener) {

        Repository<BemEntity> bemEntityRepository = new Repository<>(BemEntity.class);
        bemEntityRepository.deleteRealmObject(bemEntityRepository.getById(idBem));

        listener.onDeletarBem();

    }

    @Override
    public void buscarBemEntity(int idBem, IBemPresenter listener) {

        Repository<BemEntity> bemEntityRepository = new Repository<>(BemEntity.class);
        BemEntity bemEntity = bemEntityRepository.getById(idBem);
        listener.EditarBemEntity(bemEntity);

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

    @Override
    public void PopularListaTemNumeroTombo(Context context, IBemPresenter listener) {
        ArrayList<String> lista = new ArrayList<>();
        lista.add("Não");
        lista.add("Sim");

        listener.PopularListaTemNumeroTombo(lista);
    }
}
