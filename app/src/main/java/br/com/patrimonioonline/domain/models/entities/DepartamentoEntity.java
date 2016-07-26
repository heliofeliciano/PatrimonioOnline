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

    private int id;
    private String descricao;
    private String nomeresponsavel;
    private String emailresponsavel;
    private String limite;
    private String telefone;
    private String email;
    private String fax;
    private String ramal;
    private String instituicao;

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

    public String getNomeresponsavel() {
        return nomeresponsavel;
    }

    public void setNomeresponsavel(String nomeresponsavel) {
        this.nomeresponsavel = nomeresponsavel;
    }

    public String getEmailresponsavel() {
        return emailresponsavel;
    }

    public void setEmailresponsavel(String emailresponsavel) {
        this.emailresponsavel = emailresponsavel;
    }

    public String getLimite() {
        return limite;
    }

    public void setLimite(String limite) {
        this.limite = limite;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getRamal() {
        return ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
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
            .registerTypeAdapter(this.getClass(), new DepartamentoSerializer())
            .create();

        String json = gson.toJson(this);

        return json;
    }

    static class DepartamentoSerializer implements JsonSerializer<DepartamentoEntity> {

        @Override
        public JsonElement serialize(DepartamentoEntity src, Type typeOfSrc, JsonSerializationContext context) {
            final JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", src.getId());
            jsonObject.addProperty("descricao", src.getDescricao());
            jsonObject.addProperty("nomeresponsavel", src.getNomeresponsavel());
            jsonObject.addProperty("emailresponsavel", src.getEmailresponsavel());
            jsonObject.addProperty("limite", src.getLimite());
            jsonObject.addProperty("telefone", src.getTelefone());
            jsonObject.addProperty("email", src.getEmail());
            jsonObject.addProperty("fax", src.getFax());
            jsonObject.addProperty("ramal", src.getRamal());
            jsonObject.addProperty("instituicao", src.getInstituicao());

            return jsonObject;
        }
    }

}
