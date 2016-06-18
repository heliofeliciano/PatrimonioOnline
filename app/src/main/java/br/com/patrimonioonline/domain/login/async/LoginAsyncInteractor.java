package br.com.patrimonioonline.domain.login.async;

import android.content.Context;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import br.com.patrimonioonline.domain.consts.DomainConst;
import br.com.patrimonioonline.domain.consts.HostConst;
import br.com.patrimonioonline.domain.consts.URLConst;
import br.com.patrimonioonline.domain.consts.UsuarioPreferenceConst;
import br.com.patrimonioonline.domain.login.ILoginPresenter;
import br.com.patrimonioonline.domain.models.readonly.RetornoObjeto;
import br.com.patrimonioonline.domain.models.readonly.UsuarioReadonly;
import br.com.patrimonioonline.lib.GsonLib;
import br.com.patrimonioonline.lib.StoredPreference;
import cz.msebera.android.httpclient.Header;

/**
 * Created by helio on 13/06/16.
 */

public class LoginAsyncInteractor implements ILoginAsyncInteractor {

    @Override
    public void validarLogin(final Context context, final ILoginPresenter listener, String usuario, String senha) {

        final StringBuilder _url = new StringBuilder(HostConst.HOST_http).append(DomainConst.Dominio).append(URLConst.URL_Login);

        RequestParams requestParams = new RequestParams();
        requestParams.put("usuario", usuario);
        requestParams.put("senha", senha);

        AsyncHttpClient client = new AsyncHttpClient();
        client.post(_url.toString(), requestParams, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFailure("Error");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                JsonParser jsonParser = new JsonParser();
                JsonElement s = jsonParser.parse(responseString);

                try {

                    Object o = GsonLib.fromJsonObject(responseString, new RetornoObjeto<UsuarioReadonly>());
                    RetornoObjeto<UsuarioReadonly> _usuario= (RetornoObjeto<UsuarioReadonly>) o;

                    StoredPreference _pref = new StoredPreference(context, UsuarioPreferenceConst.USUARIO_PREF);
                    Object obj = _pref.buscarObjeto(new RetornoObjeto<UsuarioReadonly>());
                    //_pref.limparObjeto(_usuario);
                    //_pref.salvarObjeto(_usuario);

                    listener.onSuccess(responseString);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

    }
}
