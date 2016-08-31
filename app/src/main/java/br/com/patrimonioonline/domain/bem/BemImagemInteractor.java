package br.com.patrimonioonline.domain.bem;

import br.com.patrimonioonline.domain.models.entities.BemEntity;
import br.com.patrimonioonline.domain.models.entities.BemImagensEntity;
import br.com.patrimonioonline.domain.repos.Repository;
import io.realm.Realm;

/**
 * Created by helio on 09/07/16.
 */

public class BemImagemInteractor implements IBemImageInteractor {

    @Override
    public void salvarImagem(IBemImagemPresenter listener, int idBem, String descricao, String caminho) {

        Realm realm = Realm.getDefaultInstance();

        Repository<BemImagensEntity> repositoryImagem = new Repository<>(BemImagensEntity.class);

        realm.beginTransaction();
        BemImagensEntity entityImagem = realm.copyToRealm(new BemImagensEntity());
        entityImagem.setId(repositoryImagem.getProximoId());
        entityImagem.setDescricao(descricao);
        entityImagem.setCaminho(caminho);
        realm.copyToRealmOrUpdate(entityImagem);

        Repository<BemEntity> repository = new Repository<>(BemEntity.class);
        BemEntity _bemEntity = repository.getById(idBem);
        _bemEntity.getListaBemImageEntities().add(entityImagem);

        realm.copyToRealmOrUpdate(_bemEntity);

        realm.commitTransaction();

        listener.onSalvarImagem();
    }

    @Override
    public void buscarImagens(IBemImagemPresenter listener, int idBem) {

        Repository<BemEntity> _repository = new Repository<>(BemEntity.class);
        BemEntity _entity = _repository.getById(idBem);

        if (_entity != null) {
            listener.onBuscarImagem(_entity.getListaBemImageEntities().where().findAll());
        } else {
            listener.onBuscarImagem(null);
        }


    }
}
