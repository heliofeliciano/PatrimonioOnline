package br.com.patrimonioonline.domain.models.readonly;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by helio on 15/06/16.
 */

public class BemReadonly {

    public int _id;
    public String descricao;
    public ClassificacaoReadonly classificacaoEntity;
    public AquisicaoReadonly aquisicaoEntity;
    public DepartamentoReadonly departamentoEntity;
    public ConvenioReadonly convenioEntity;
    public SituacaoReadonly situacaoEntity;
    public String numeroPlaca;
    public Double valorAquisicao;
    public Double valorResidual;
    public Date dataAquisicao;


}
