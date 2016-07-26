package br.com.patrimonioonline.domain.SolicitarPlacas;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import br.com.patrimonioonline.domain.consts.DomainConst;
import br.com.patrimonioonline.domain.consts.HostConst;
import br.com.patrimonioonline.domain.consts.URLConst;
import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;
import br.com.patrimonioonline.domain.models.readonly.UsuarioReadonly;
import cz.msebera.android.httpclient.Header;

/**
 * Created by helio on 20/07/16.
 */

public class SolicitarPlacasInteractor implements ISolicitarPlacasInteractor {

    @Override
    public void Salvar(final ISolicitarPlacasPresenter listener, DepartamentoEntity departamentoEntity, UsuarioReadonly usuarioReadonly, int
            quantidadeSolicitada) {

        final StringBuilder _url = new StringBuilder(HostConst.HOST_http).append(DomainConst.Dominio).append(URLConst
                .URL_SolicitarPlacas);

        RequestParams _requestParams = new RequestParams();
        _requestParams.put("coddepto", (int)Double.parseDouble(departamentoEntity.id));
        _requestParams.put("idusuariosolicitante", 1);
        _requestParams.put("quantidade", quantidadeSolicitada);

        AsyncHttpClient client = new AsyncHttpClient();
        client.post(_url.toString(), _requestParams, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.Error("Ocorreu um erro!");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                // Tenta salvar no servidor, em caso de sucesso é hora de salvar no banco local
                listener.SalvarResult(responseString);
            }
        });

        //listener.SalvarResult("Salvo com sucesso");
    }
}
