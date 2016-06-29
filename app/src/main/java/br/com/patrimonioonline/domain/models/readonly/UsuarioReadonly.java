package br.com.patrimonioonline.domain.models.readonly;

import java.util.List;

/**
 * Created by helio on 13/06/16.
 */

public class UsuarioReadonly {

    public String nome;
    public String login;
    public int usuarioativo;
    public String email;
    public List<DepartamentoReadonly> departamentos;

    public UsuarioReadonly() {
    }

    public UsuarioReadonly(String nome, String login, int usuarioativo, String email, List<DepartamentoReadonly> departamentos) {
        this.nome = nome;
        this.login = login;
        this.usuarioativo = usuarioativo;
        this.email = email;
        this.departamentos = departamentos;
    }
}
