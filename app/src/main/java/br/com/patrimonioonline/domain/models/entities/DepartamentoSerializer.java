package br.com.patrimonioonline.domain.models.entities;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by helio on 30/06/16.
 */

public class DepartamentoSerializer implements JsonSerializer<DepartamentoEntity> {

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
