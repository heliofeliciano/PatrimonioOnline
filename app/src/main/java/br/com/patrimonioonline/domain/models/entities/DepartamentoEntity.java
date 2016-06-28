package br.com.patrimonioonline.domain.models.entities;

import io.realm.RealmObject;

/**
 * Created by helio on 15/06/16.
 */

public class DepartamentoEntity extends RealmObject {

    public String id;
    public String descricao;
    public String nomeresponsavel;
    public String emailresponsavel;
    public String limite;
    public String telefone;
    public String email;
    public String fax;
    public String ramal;
    public String instituicao;

    @Override
    public String toString() {
        return descricao;
    }
}
