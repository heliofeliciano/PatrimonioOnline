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

public class AquisicaoEntity extends RealmObject {

    private int id;
    private String descricao;

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

    @Override
    public String toString() {
        return descricao;
    }

    public String converterParaJson(){

        Gson gson = null;

        gson = new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .registerTypeAdapter(this.getClass(), new AquisicaoEntity.AquisicaoSerializer())
                .create();

        String json = gson.toJson(this);

        return json;
    }

    static class AquisicaoSerializer implements JsonSerializer<AquisicaoEntity> {

        @Override
        public JsonElement serialize(AquisicaoEntity src, Type typeOfSrc, JsonSerializationContext context) {
            final JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", src.id);
            jsonObject.addProperty("descricao", src.descricao);

            return jsonObject;
        }
    }
}
