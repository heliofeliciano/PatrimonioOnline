package br.com.patrimonioonline.lib;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import io.realm.RealmObject;

/**
 * Created by helio on 14/06/16.
 */

public class StoredPreference {

    private static SharedPreferences preferencias;
    private static SharedPreferences.Editor editor;
    private static Context ctx;

    public StoredPreference(Context ctx, String prefsName) {
        this.preferencias = ctx.getSharedPreferences(prefsName, ctx.MODE_PRIVATE);
        this.editor = preferencias.edit();
        this.ctx = ctx;
    }

    public static void listarPreferences(){
        preferencias.getAll();
    }

    public static void salvarObjeto(Object obj){
        try {

            Gson _gson = new Gson();
            String _json = _gson.toJson(obj);
            editor.putString(obj.getClass().getSimpleName(), _json);
            editor.commit();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    /**
     * Método utilizado para salvar objetos via Shared Preferences
     * Ao passar o objeto como parametro, o metodo converterá este objeto em um json.
     * O objeto será armazenado em formato json na Shared Preferences
     * @param obj   Objeto que se deseja armazenar
     * @param nomePreferencia   Nome de referencia para realizar uma futura busca
     */
    public static void salvarObjeto(Object obj, String nomePreferencia){
        try {

            Gson _gson = new Gson();
            String _json = _gson.toJson(obj);
            editor.putString(nomePreferencia, _json);
            editor.commit();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public static void salvarJson(String json, String nomeObjeto){
        try {

            Gson _gson = new Gson();
            editor.putString(nomeObjeto, json);
            editor.commit();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public static void salvarObjetoRealm(RealmObject obj, String json){
        try {

            editor.putString(obj.getClass().getSimpleName(), json);
            editor.commit();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public String buscarObjetoJson(String nomePreferencia){
        String _json = preferencias.getString(nomePreferencia, "");

        return _json;
    }

    /**
     * Método responsável por retornar um objeto previamente inserido nas Shared Preferences
     * @param obj   Objeto que se deseja buscar
     * @param nomePreferencia   Nome de referencia para buscar o objeto inserido
     * @return  Objeto
     */
    public Object buscarObjeto(Object obj, String nomePreferencia){

        String _json = preferencias.getString(nomePreferencia, "");

        return new Gson().fromJson(_json, obj.getClass());
    }

    public static void limparObjeto(Object obj){

        SharedPreferences.Editor _editor = preferencias.edit();
        _editor.remove(obj.getClass().getSimpleName());
        _editor.commit();

    }

    public static void limparTodos(){

        SharedPreferences.Editor _editor = preferencias.edit();
        _editor.clear();
        _editor.commit();

    }

}
