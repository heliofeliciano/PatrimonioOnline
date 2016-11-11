package br.com.patrimonioonline.domain.repos;

import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;
import br.com.patrimonioonline.domain.models.entities.UsuarioEntity;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by helio on 11/11/16.
 */

public class RepositorioUsuario extends Repository<UsuarioEntity>  {

    public RepositorioUsuario() {
        super(UsuarioEntity.class);
    }

    public void atualizarDepartamentoAtual(final UsuarioEntity usuarioEntity, final DepartamentoEntity departamentoEntity){

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                UsuarioEntity usuarioLogado = realm.createObject(UsuarioEntity.class);
                usuarioLogado = realm.where(UsuarioEntity.class).equalTo("login", usuarioEntity.login).findFirst();
                usuarioLogado.departamentoAtual = departamentoEntity;
            }
        });

    }

    public UsuarioEntity getByLogin(String login){
        return realm.where(clazz).equalTo("login", login).findFirst();
    }

}
