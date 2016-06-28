package br.com.patrimonioonline.domain.models.readonly;

/**
 * Created by helio on 15/06/16.
 */

public class DepartamentoReadonly {

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

    public DepartamentoReadonly() {
    }

    public DepartamentoReadonly(String id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
}
