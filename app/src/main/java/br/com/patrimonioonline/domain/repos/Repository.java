package br.com.patrimonioonline.domain.repos;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
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

    public void createObjectFromJson(String json){
        realm.beginTransaction();
        realm.createObjectFromJson(clazz, json);
        realm.commitTransaction();
    }

    public List<T> all(){
        return realm.where(clazz).findAll();
    }

    public T getByLogin(String login){
        return realm.where(clazz).equalTo("login", login).findFirst();
    }

    public void deleteAll(){

        final RealmResults results = realm.where(clazz).findAll();
        realm.executeTransaction(new Realm.Transaction(){
            @Override
            public void execute(Realm realm) {
                results.deleteAllFromRealm();
            }
        });

    }


}
