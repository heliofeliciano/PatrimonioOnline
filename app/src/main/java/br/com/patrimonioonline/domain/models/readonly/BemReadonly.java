package br.com.patrimonioonline.domain.models.readonly;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by helio on 15/06/16.
 */

public class BemReadonly extends RealmObject {

    public int _id;
    public String descricao;
    public ClassificacaoEntity classificacaoEntity;
    public AquisicaoReadonly aquisicaoEntity;
    public DepartamentoEntity departamentoEntity;
    public ConvenioEntity convenioEntity;
    public SituacaoEntity situacaoEntity;
    public String numeroPlaca;
    public Double valorAquisicao;
    public Double valorResidual;
    public Date dataAquisicao;


}
