package br.com.patrimonioonline.domain.models.entities;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import io.realm.RealmObject;

/**
 * Created by helio on 24/06/16.
 */

public class BemTipoDepreciacaoEntity extends RealmObject {

    private int id;
    private String descricao;
    private Double quantidadeano;
    private Double percentualdepreciacaoano;
    private String observacao;
    private Boolean edepreciavel;

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

    public Double getQuantidadeano() {
        return quantidadeano;
    }

    public void setQuantidadeano(Double quantidadeano) {
        this.quantidadeano = quantidadeano;
    }

    public Double getPercentualdepreciacaoano() {
        return percentualdepreciacaoano;
    }

    public void setPercentualdepreciacaoano(Double percentualdepreciacaoano) {
        this.percentualdepreciacaoano = percentualdepreciacaoano;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Boolean getEdepreciavel() {
        return edepreciavel;
    }

    public void setEdepreciavel(Boolean edepreciavel) {
        this.edepreciavel = edepreciavel;
    }

    @Override
    public String toString() {
        return descricao;
    }

}
