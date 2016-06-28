package br.com.patrimonioonline.domain.models.entities;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by helio on 15/06/16.
 */

public class BemEntity extends RealmObject {

    public int _id;
    public String descricao;
    public BemTipoEntity bemTipoEntity;
    public BemTipoDepreciacaoEntity bemTipoDepreciacaoEntity;
    public ClassificacaoEntity classificacaoEntity;
    public AquisicaoEntity aquisicaoEntity;
    public DepartamentoEntity departamentoEntity;
    public ConvenioEntity convenioEntity;
    public SituacaoEntity situacaoEntity;
    public String numeroPlaca;
    public Double valorAquisicao;
    public Double valorResidual;
    public Date dataAquisicao;


}
