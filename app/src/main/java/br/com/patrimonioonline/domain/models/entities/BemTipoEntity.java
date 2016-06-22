package br.com.patrimonioonline.domain.models.entities;

import io.realm.RealmObject;

/**
 * Created by helio on 20/06/16.
 */

public class BemTipoEntity extends RealmObject {

    private int id;
    private String descricao;

    @Override
    public String toString() {
        return descricao;
    }
}
