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
 * Created by helio on 15/06/16.
 */

public class ClassificacaoEntity extends RealmObject {

    public int id;
    public String descricao;

    /*public ClassificacaoEntity(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }*/

    @Override
    public String toString() {
        return descricao;
    }

}
