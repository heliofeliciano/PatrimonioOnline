package br.com.patrimonioonline.domain.models.entities;

import io.realm.RealmObject;

/**
 * Created by helio on 24/06/16.
 */

public class BemTipoDepreciacaoEntity extends RealmObject {

    public int id;
    public String descricao;
    public Double quantidadeano;
    public Double percentualdepreciacaoano;
    public String observacao;
    public Boolean edepreciavel;

    @Override
    public String toString() {
        return descricao;
    }
}
