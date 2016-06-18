package br.com.patrimonioonline.lib;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;

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

    public static void salvarObjeto(Object obj){
        Gson _gson = new Gson();
        String _json = _gson.toJson(obj);
        editor.putString(obj.getClass().getSimpleName(), _json);
        editor.commit();

        Toast.makeText(ctx, "Preferencias salva com sucesso", Toast.LENGTH_LONG).show();
    }

    public Object buscarObjeto(Object obj){
        String _json = preferencias.getString(obj.getClass().getSimpleName(), "");

        return new Gson().fromJson(_json, obj.getClass());
    }

    public static void clearObjectPreference(Object obj){
        SharedPreferences.Editor _editor = preferencias.edit();
        _editor.remove(obj.getClass().getSimpleName());
        _editor.commit();
    }

}
