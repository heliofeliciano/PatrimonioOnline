package br.com.patrimonioonline.domain.bem;

import br.com.patrimonioonline.domain.models.entities.BemEntity;
import br.com.patrimonioonline.domain.repos.Repository;
import io.realm.Realm;

/**
 * Created by helio on 13/07/16.
 */

public class MapsInteractor implements IMapsInteractor {

    @Override
    public void Salvar(int idBem, String latitude, String longitude, IMapsPresenter listener) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        Repository<BemEntity> repository = new Repository<>(BemEntity.class);
        BemEntity _bemEntity = repository.getById(idBem);
        _bemEntity.setLatitude(latitude);
        _bemEntity.setLongitude(longitude);

        realm.copyToRealmOrUpdate(_bemEntity);

        realm.commitTransaction();

        listener.salvarResult();

    }
}
