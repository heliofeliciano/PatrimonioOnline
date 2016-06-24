package br.com.patrimonioonline.domain.models.entities;

import io.realm.RealmObject;

/**
 * Created by helio on 15/06/16.
 */

public class SituacaoEntity extends RealmObject {
    public int _id;
    public String descricao;

    @Override
    public String toString() {
        return descricao;
    }
}
