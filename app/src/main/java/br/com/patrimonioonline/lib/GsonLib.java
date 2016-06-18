package br.com.patrimonioonline.lib;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

/**
 * Created by helio on 13/06/16.
 */

public class GsonLib {

    public static HashMap<String, String> getParams(String json){
        Type stringStringMap = new TypeToken<HashMap<String, String>>(){}.getType();
        final HashMap<String,String> map = new Gson().fromJson(json, stringStringMap);
        return map;
    }

    public static Object fromJsonObject(String response, Object obj){
        JsonParser _jsonParser = new JsonParser();
        JsonObject _jsonObject = (JsonObject)_jsonParser.parse(response);

        Object object = new Gson().fromJson(_jsonObject, obj.getClass());

        return object;
    }

    public static List<Object> fromJsonList(String response, Object obj){
        JsonParser _jsonParser = new JsonParser();
        JsonObject _jsonObject = (JsonObject)_jsonParser.parse(response);
        JsonArray _jsonArray = _jsonObject.getAsJsonArray("o");

        List<Object> objects = new Gson().fromJson(_jsonArray, new TypeToken<List<Object>>() {}.getType());

        return objects;
    }

    public static String converterObjetoParaJson(Object object){
        String _json = new Gson().toJson(object);
        return _json;
    }

}
