package br.com.patrimonioonline.domain.models.readonly;

/**
 * Created by helio on 15/06/16.
 */

public class DepartamentoReadonly {

    private int id;
    private String descricao;
    private String nomeresponsavel;
    private String emailresponsavel;
    private String limite;
    private String telefone;
    private String email;
    private String fax;
    private String ramal;
    private int instituicao;

    public DepartamentoReadonly() {
    }

    public DepartamentoReadonly(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNomeresponsavel() {
        return nomeresponsavel;
    }

    public void setNomeresponsavel(String nomeresponsavel) {
        this.nomeresponsavel = nomeresponsavel;
    }

    public String getEmailresponsavel() {
        return emailresponsavel;
    }

    public void setEmailresponsavel(String emailresponsavel) {
        this.emailresponsavel = emailresponsavel;
    }

    public String getLimite() {
        return limite;
    }

    public void setLimite(String limite) {
        this.limite = limite;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getRamal() {
        return ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    public int getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(int instituicao) {
        this.instituicao = instituicao;
    }
}
