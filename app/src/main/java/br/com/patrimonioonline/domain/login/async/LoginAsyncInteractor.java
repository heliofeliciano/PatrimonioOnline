package br.com.patrimonioonline.domain.login.async;

import android.content.Context;
import android.os.Build;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.ArrayList;

import br.com.patrimonioonline.domain.consts.DomainConst;
import br.com.patrimonioonline.domain.consts.HostConst;
import br.com.patrimonioonline.domain.consts.URLConst;
import br.com.patrimonioonline.domain.consts.UsuarioPreferenceConst;
import br.com.patrimonioonline.domain.login.ILoginPresenter;
import br.com.patrimonioonline.domain.models.entities.UsuarioEntity;
import br.com.patrimonioonline.domain.models.readonly.DepartamentoReadonly;
import br.com.patrimonioonline.domain.models.readonly.RetornoObjeto;
import br.com.patrimonioonline.domain.models.readonly.UsuarioReadonly;
import br.com.patrimonioonline.domain.repos.Repository;
import br.com.patrimonioonline.lib.GsonLib;
import br.com.patrimonioonline.lib.StoredPreference;
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

                    Object objO = _usuario.o;
                    LinkedTreeMap objLinked = (LinkedTreeMap) objO;
                    ArrayList<DepartamentoReadonly> departamentoReadonlyArrayList = (ArrayList<DepartamentoReadonly>) ((LinkedTreeMap) objO).get("departamentos");

                    // Converter UsuárioEntity em UsuarioReadonly
                    UsuarioReadonly _usuarioReadonly = new UsuarioReadonly(
                            (String) ((LinkedTreeMap) objO).get("nome"),
                            (String) ((LinkedTreeMap) objO).get("login"),
                            ((Double) ((LinkedTreeMap) objO).get("usuarioativo")).intValue(),
                            (String) ((LinkedTreeMap) objO).get("email"),
                            departamentoReadonlyArrayList
                    );

                    // Salvar as preferences do usuário
                    StoredPreference _pref = new StoredPreference(context, UsuarioPreferenceConst.USUARIO_PREF);
                    _pref.limparObjeto(_usuarioReadonly);
                    _pref.salvarObjeto(_usuarioReadonly);

                    listener.onSuccess(responseString);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

    }

    @Override
    public void cadastrarRegIdDispositivo(Context context, ILoginPresenter listener) {

        StoredPreference _pref = new StoredPreference(context, UsuarioPreferenceConst.USUARIO_PREF);
        UsuarioReadonly _usuarioReadonly = (UsuarioReadonly) _pref.buscarObjeto(new UsuarioReadonly());

        // Cadastrar o registro do dispositivo
        final StringBuilder _url = new StringBuilder(HostConst.HOST_http).append(DomainConst.Dominio).append(URLConst.URL_CadastrarDispositivo);

        RequestParams requestParams = new RequestParams();
        requestParams.put("login", _usuarioReadonly.login);
        requestParams.put("regid", "");
        requestParams.put("modelo", Build.MODEL);
        requestParams.put("dispositivo", Build.DEVICE);

        listener.cadastrarRegIdDispositivoResult();

    }

    /*@Override
    public void buscarDepartamentosDoUsuario(Context context, final ILoginPresenter listener) {

        Repository<UsuarioEntity> usuarioEntityRepository = new Repository<>(UsuarioEntity.class);
        //listener.PopularListaAquisicao(aquisicaoEntityRepository.all());

        List<DepartamentoEntity> departamentoEntities = usuarioEntityRepository.getByLogin("dbseller").departamentos;

        listener.onDepartamentosPorUsuario(departamentoEntities);

    }*/

}
