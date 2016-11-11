package br.com.patrimonioonline.domain.repos;

import java.util.List;

import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;
import br.com.patrimonioonline.domain.models.entities.UsuarioEntity;
import br.com.patrimonioonline.lib.StoredPreference;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by helio on 11/11/16.
 */

public class RepositoryDepartamento extends Repository<DepartamentoEntity> {

    public DepartamentoEntity getDepartamentoAtual(){

        return realm.where(DepartamentoEntity.class).equalTo("departamentoAtual", true).findFirst();
    }

    /*public void atualizarDepartamentoAtual(final DepartamentoEntity departamentoEntity){

        final DepartamentoEntity[] departamentoAtual = new DepartamentoEntity[1];
        final UsuarioEntity[] usuarioEntity = new UsuarioEntity[1];

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                usuarioEntity[0] = realm.createObject(UsuarioEntity.class);
                usuarioEntity[0] = realm.where(UsuarioEntity.class).equalTo("login","dbseller").findFirst();

                if (usuarioEntity[0] != null) {


                    departamentoAtual[0] = realm.where(DepartamentoEntity.class).equalTo("id", departamentoEntity.getId()).findFirst();

                    RealmResults<DepartamentoEntity> result = usuarioEntity[0].departamentos.where().equalTo("id",departamentoEntity.getId()).findAll();
                    result.deleteAllFromRealm();
                }

                *//*DepartamentoEntity departamentoAtual = realm.createObject(DepartamentoEntity.class);
                departamentoAtual = realm.where(DepartamentoEntity.class).equalTo("id", departamentoEntity.getId()).findFirst();
                departamentoAtual.setDepartamentoAtual(true);*//*
            }
        });

        *//*realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                departamentoAtual[0] = realm.createObject(DepartamentoEntity.class);
                departamentoAtual[0].setDepartamentoAtual(true);
                usuarioEntity[0].departamentos.add(departamentoAtual[0]);
            }
        });*//*

    }*/
}
