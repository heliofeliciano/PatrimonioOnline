package br.com.patrimonioonline.domain.login.async;

import android.content.Context;
import android.os.Build;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import br.com.patrimonioonline.R;
import br.com.patrimonioonline.domain.consts.DomainConst;
import br.com.patrimonioonline.domain.consts.HostConst;
import br.com.patrimonioonline.domain.consts.PreferenceConst;
import br.com.patrimonioonline.domain.consts.URLConst;
import br.com.patrimonioonline.domain.login.ILoginPresenter;
import br.com.patrimonioonline.domain.models.entities.UsuarioEntity;
import br.com.patrimonioonline.domain.models.readonly.RetornoObjeto;
import br.com.patrimonioonline.domain.repos.Repository;
import br.com.patrimonioonline.lib.GsonLib;
import br.com.patrimonioonline.lib.StoredPreference;
import cz.msebera.android.httpclient.Header;

/**
 * Created by helio on 13/06/16.
 */

public class LoginAsyncInteractor implements ILoginAsyncInteractor {

    @Override
    public void realizarLogin(final Context context, final ILoginPresenter listener, final String usuario, String senha) {

        // TODO: 11/9/16 checar se o usuário já existe no banco de dados local


        final StringBuilder _url = new StringBuilder(HostConst.HOST_http).append(DomainConst.Dominio).append(URLConst.URL_Login);

        RequestParams requestParams = new RequestParams();
        requestParams.put("usuario", usuario);
        requestParams.put("senha", senha);

        AsyncHttpClient client = new AsyncHttpClient();
        client.post(_url.toString(), requestParams, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.falhouRealizarLogin(context.getString(R.string.msgConexaoErro));
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                JsonParser jsonParser = new JsonParser();
                JsonElement s = jsonParser.parse(responseString);

                try {

                    Object obj = GsonLib.fromJsonObject(responseString, new RetornoObjeto<UsuarioEntity>());
                    RetornoObjeto<UsuarioEntity> _usuario = (RetornoObjeto<UsuarioEntity>) obj;

                    String jsonUsuario = GsonLib.converterObjetoParaJson(_usuario.o);

                    if (_usuario.s == 0) {
                        throw new Exception(_usuario.m);
                    }

                    Repository<UsuarioEntity> usuarioEntityRepository = new Repository<>(UsuarioEntity.class);

                    // TODO: 11/8/16 Provisorio Não deve excluir o usuário
                    usuarioEntityRepository.deleteAll();
                    usuarioEntityRepository.createObjectFromJson(jsonUsuario);

                    StoredPreference _pref = new StoredPreference(context, PreferenceConst.PREFERENCES);
                    _pref.salvar(usuario, PreferenceConst.PrefUsuarioLogin);

                    listener.sucessoRealizarLogin(responseString);

                } catch (Exception e) {
                    // e.printStackTrace();
                    listener.falhouRealizarLogin(e.getMessage());

                }

            }

        });

    }

    @Override
    public void cadastrarRegIdDispositivo(Context context, final ILoginPresenter listener) {

        StoredPreference _pref = new StoredPreference(context, PreferenceConst.PREFERENCES);
        String usuarioLogin = _pref.buscar(PreferenceConst.PrefUsuarioLogin);

        // Cadastrar o registro do dispositivo
        final StringBuilder _url = new StringBuilder(HostConst.HOST_http).append(DomainConst.Dominio).append(URLConst.URL_CadastrarDispositivo);

        RequestParams requestParams = new RequestParams();
        requestParams.put("login", usuarioLogin);
        requestParams.put("regid", "");
        requestParams.put("modelo", Build.MODEL);
        requestParams.put("dispositivo", Build.DEVICE);

        AsyncHttpClient client = new AsyncHttpClient();
        client.post(_url.toString(), requestParams, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.falhouRealizarLogin("Ocorreu um erro ao cadastrar o dispositivo");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                listener.cadastrarRegIdDispositivoResult();

            }
        });

        //listener.cadastrarRegIdDispositivoResult();

    }

    public void cadastrarRegIdDispositivo(Context context, String regId) {

        StoredPreference _pref = new StoredPreference(context, PreferenceConst.PREFERENCES);
        String usuarioLogin = _pref.buscar(PreferenceConst.PrefUsuarioLogin);

        // Cadastrar o registro do dispositivo
        final StringBuilder _url = new StringBuilder(HostConst.HOST_http).append(DomainConst.Dominio).append(URLConst.URL_CadastrarDispositivo);

        RequestParams requestParams = new RequestParams();
        requestParams.put("login", usuarioLogin);
        requestParams.put("regid", regId);
        requestParams.put("modelo", Build.MODEL);
        requestParams.put("dispositivo", Build.DEVICE);

        AsyncHttpClient client = new AsyncHttpClient();
        client.post(_url.toString(), requestParams, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

            }
        });

        //listener.cadastrarRegIdDispositivoResult();

    }

}
