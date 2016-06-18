package br.com.patrimonioonline.domain.repos;

import br.com.patrimonioonline.domain.models.entities.AquisicaoEntity;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by helio on 15/06/16.
 */

public class Repository<T extends RealmObject> {

    private Realm realm = Realm.getDefaultInstance();
    private Class<T> clazz;

    public Repository(Class<T> clazz) {
        this.clazz = clazz;
    }

    public void create(RealmObject realmObject){
        realm.beginTransaction();
        RealmObject object = realm.copyToRealm(realmObject);
        realm.commitTransaction();
    }

    public void createAllFromJson(String json){
        realm.beginTransaction();
        realm.createAllFromJson(clazz, json);
        realm.commitTransaction();
    }

    public RealmResults all(){
        return realm.where(clazz).findAll();
    }

    public void deleteAll(){

        final RealmResults results = all();
        realm.executeTransaction(new Realm.Transaction(){
            @Override
            public void execute(Realm realm) {
                results.deleteAllFromRealm();
            }
        });

    }


}
