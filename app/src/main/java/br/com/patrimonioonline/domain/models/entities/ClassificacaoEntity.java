package br.com.patrimonioonline.domain.models.entities;

import io.realm.RealmObject;

/**
 * Created by helio on 15/06/16.
 */

public class ClassificacaoEntity extends RealmObject {

    public int _id;
    public String descricao;

    /*public ClassificacaoEntity(int _id, String descricao) {
        this._id = _id;
        this.descricao = descricao;
    }*/

    @Override
    public String toString() {
        return descricao;
    }
}
