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

public class DepartamentoEntity extends RealmObject {

    public String id;
    public String descricao;
    public String nomeresponsavel;
    public String emailresponsavel;
    public String limite;
    public String telefone;
    public String email;
    public String fax;
    public String ramal;
    public String instituicao;

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
            .registerTypeAdapter(this.getClass(), new DepartamentoSerializer())
            .create();

        String json = gson.toJson(this);

        return json;
    }

    static class DepartamentoSerializer implements JsonSerializer<DepartamentoEntity> {

        @Override
        public JsonElement serialize(DepartamentoEntity src, Type typeOfSrc, JsonSerializationContext context) {
            final JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", src.id);
            jsonObject.addProperty("descricao", src.descricao);
            jsonObject.addProperty("nomeresponsavel", src.nomeresponsavel);
            jsonObject.addProperty("emailresponsavel", src.emailresponsavel);
            jsonObject.addProperty("limite", src.limite);
            jsonObject.addProperty("telefone", src.telefone);
            jsonObject.addProperty("email", src.email);
            jsonObject.addProperty("fax", src.fax);
            jsonObject.addProperty("ramal", src.ramal);
            jsonObject.addProperty("instituicao", src.instituicao);

            return jsonObject;
        }
    }

}
