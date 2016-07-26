package br.com.patrimonioonline.domain.models.entities;

import io.realm.RealmObject;

/**
 * Created by helio on 25/07/16.
 */

public class SolicitarPlacasEntity extends RealmObject {

    private int id;
    private DepartamentoEntity departamentoEntity;
    private int quantidadeSolicitada;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DepartamentoEntity getDepartamentoEntity() {
        return departamentoEntity;
    }

    public void setDepartamentoEntity(DepartamentoEntity departamentoEntity) {
        this.departamentoEntity = departamentoEntity;
    }

    public int getQuantidadeSolicitada() {
        return quantidadeSolicitada;
    }

    public void setQuantidadeSolicitada(int quantidadeSolicitada) {
        this.quantidadeSolicitada = quantidadeSolicitada;
    }
}
