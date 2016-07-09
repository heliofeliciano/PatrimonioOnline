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

public class ConvenioEntity extends RealmObject {

    public int id;
    public String descricao;

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
                .registerTypeAdapter(this.getClass(), new ConvenioEntity.ConvenioSerializer())
                .create();

        String json = gson.toJson(this);

        return json;
    }

    static class ConvenioSerializer implements JsonSerializer<ConvenioEntity> {

        @Override
        public JsonElement serialize(ConvenioEntity src, Type typeOfSrc, JsonSerializationContext context) {
            final JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", src.id);
            jsonObject.addProperty("descricao", src.descricao);

            return jsonObject;
        }
    }

}
