package br.com.patrimonioonline.domain.models.entities;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by helio on 13/06/16.
 */

public class UsuarioEntity extends RealmObject {

    public String nome;
    public String login;
    public int usuarioativo;
    public String email;
    public RealmList<DepartamentoEntity> departamentos;
    public DepartamentoEntity departamentoAtual;

}
