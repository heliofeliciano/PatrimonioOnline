package br.com.patrimonioonline.domain.models.entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by helio on 15/06/16.
 */

public class BemEntity extends RealmObject {

    @PrimaryKey
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
    public String dataAquisicao;


}
