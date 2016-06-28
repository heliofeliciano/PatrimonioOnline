package br.com.patrimonioonline.domain.login.async;

import android.content.Context;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.List;

import br.com.patrimonioonline.domain.consts.DomainConst;
import br.com.patrimonioonline.domain.consts.HostConst;
import br.com.patrimonioonline.domain.consts.URLConst;
import br.com.patrimonioonline.domain.login.ILoginPresenter;
import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;
import br.com.patrimonioonline.domain.models.entities.UsuarioEntity;
import br.com.patrimonioonline.domain.models.readonly.RetornoObjeto;
import br.com.patrimonioonline.domain.repos.Repository;
import br.com.patrimonioonline.lib.GsonLib;
import cz.msebera.android.httpclient.Header;

/**
 * Created by helio on 13/06/16.
 */

public class LoginAsyncInteractor implements ILoginAsyncInteractor {

    @Override
    public void validarLogin(final Context context, final ILoginPresenter listener, final String usuario, String senha) {

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

                    Object obj = GsonLib.fromJsonObject(responseString, new RetornoObjeto<UsuarioEntity>());
                    RetornoObjeto<UsuarioEntity> _usuario = (RetornoObjeto<UsuarioEntity>) obj;

                    String jsonUsuario;

                    jsonUsuario = GsonLib.converterObjetoParaJson(_usuario.o);

                    Repository<UsuarioEntity> usuarioEntityRepository = new Repository<>(UsuarioEntity.class);

                    // Provisorio
                    usuarioEntityRepository.deleteAll();

                    usuarioEntityRepository.createObjectFromJson(jsonUsuario);

                    /*StoredPreference _pref = new StoredPreference(context, UsuarioPreferenceConst.USUARIO_PREF);

                    _pref.limparObjeto(_usuario);
                    _pref.salvarObjeto(_usuario);*/

                    listener.onSuccess(responseString);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

    }

    @Override
    public void buscarDepartamentosDoUsuario(Context context, final ILoginPresenter listener) {

        Repository<UsuarioEntity> usuarioEntityRepository = new Repository<>(UsuarioEntity.class);
        //listener.PopularListaAquisicao(aquisicaoEntityRepository.all());

        List<DepartamentoEntity> departamentoEntities = usuarioEntityRepository.getByLogin("dbseller").departamentos;

        listener.onDepartamentosPorUsuario(departamentoEntities);

    }

}
