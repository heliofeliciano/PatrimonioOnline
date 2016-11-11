package br.com.patrimonioonline.domain.repos;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by helio on 15/06/16.
 */

public class Repository<T extends RealmObject> {

    public Realm realm = Realm.getDefaultInstance();
    public Class<T> clazz;

    public Repository() {
    }

    public Repository(Class<T> clazz) {
        this.clazz = clazz;
    }

    public Realm getInstancia() {
        return realm;
    }

    public void createOrUpdate(RealmObject realmObject){
        realm.beginTransaction();
        RealmObject object = realm.copyToRealmOrUpdate(realmObject);
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

    public int getProximoId() {

        if (allResults().size() == 0) {
            return 1;
        }

        return realm.where(clazz).max("id").intValue() + 1;
    }

    public RealmResults<T> allResults() {
        return realm.where(clazz).findAll();
    }

    public List<T> all(){
        return realm.where(clazz).findAll();
    }

    public T getByLogin(String login){
        return realm.where(clazz).equalTo("login", login).findFirst();
    }

    public T getById(int id){

        return realm.where(clazz).equalTo("id", id).findFirst();
    }

    public void deleteRealmObject(RealmObject realmObject){
        realm.beginTransaction();
        realmObject.deleteFromRealm();
        realm.commitTransaction();
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
