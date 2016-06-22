package br.com.patrimonioonline.domain.configuracao;

import android.content.Context;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import br.com.patrimonioonline.domain.consts.DomainConst;
import br.com.patrimonioonline.domain.consts.HostConst;
import br.com.patrimonioonline.domain.consts.URLConst;
import br.com.patrimonioonline.domain.models.entities.AquisicaoEntity;
import br.com.patrimonioonline.domain.models.entities.BemTipoEntity;
import br.com.patrimonioonline.domain.models.entities.ClassificacaoEntity;
import br.com.patrimonioonline.domain.models.entities.ConvenioEntity;
import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;
import br.com.patrimonioonline.domain.models.entities.SituacaoEntity;
import br.com.patrimonioonline.domain.models.readonly.AquisicaoReadonly;
import br.com.patrimonioonline.domain.models.readonly.ObjetosIniciaisReadonly;
import br.com.patrimonioonline.domain.models.readonly.RetornoObjeto;
import br.com.patrimonioonline.domain.models.readonly.UsuarioReadonly;
import br.com.patrimonioonline.domain.repos.Repository;
import br.com.patrimonioonline.lib.GsonLib;
import cz.msebera.android.httpclient.Header;

/**
 * Created by helio on 15/06/16.
 */

public class SincronizacaoInteractor implements ISincronizacaoInteractor {

    @Override
    public void realizarSincronizacao(final Context context, final ISincronizacaoPresenter listener) {

        final StringBuilder _url = new StringBuilder(HostConst.HOST_http).append(DomainConst.Dominio).append(URLConst.URL_ObjetosIniciaisListar);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(_url.toString(), null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFailure("Error");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                try {

                    Object obj = GsonLib.fromJsonObject(responseString, new RetornoObjeto<ObjetosIniciaisReadonly>());
                    RetornoObjeto<ObjetosIniciaisReadonly> _objetosini = (RetornoObjeto<ObjetosIniciaisReadonly>) obj;

                    String jsonAquisicao;
                    String jsonClassificacao;
                    String jsonConvenio;
                    String jsonSituacao;
                    String jsonDepartamento;
                    String jsonBemtipos;

                    Object objO = _objetosini.o;
                    LinkedTreeMap objLinked = (LinkedTreeMap) objO;

                    jsonAquisicao = GsonLib.converterObjetoParaJson(objLinked.get("aquisicao"));
                    jsonClassificacao = GsonLib.converterObjetoParaJson(objLinked.get("classificacao"));
                    jsonConvenio = GsonLib.converterObjetoParaJson(objLinked.get("convenio"));
                    jsonSituacao = GsonLib.converterObjetoParaJson(objLinked.get("situacao"));
                    jsonDepartamento = GsonLib.converterObjetoParaJson(objLinked.get("departamento"));
                    jsonBemtipos = GsonLib.converterObjetoParaJson(objLinked.get("bemtipos"));

                    // Salvar as aquisições no Realm
                    Repository<AquisicaoEntity> repositoryAquisicao = new Repository<AquisicaoEntity>(AquisicaoEntity.class);
                    Repository<ClassificacaoEntity> repositoryClassificacao = new Repository<ClassificacaoEntity>(ClassificacaoEntity.class);
                    Repository<ConvenioEntity> repositoryConvenio = new Repository<ConvenioEntity>(ConvenioEntity.class);
                    Repository<SituacaoEntity> repositorySituacao = new Repository<SituacaoEntity>(SituacaoEntity.class);
                    Repository<DepartamentoEntity> repositoryDepartamento = new Repository<DepartamentoEntity>(DepartamentoEntity.class);
                    Repository<BemTipoEntity> repositoryBemtipo = new Repository<BemTipoEntity>(BemTipoEntity.class);

                    // Exclui todos os cadastros
                    repositoryAquisicao.deleteAll();
                    repositoryClassificacao.deleteAll();
                    repositoryConvenio.deleteAll();
                    repositorySituacao.deleteAll();
                    repositoryDepartamento.deleteAll();
                    repositoryBemtipo.deleteAll();

                    // Inclui todos os cadastros
                    repositoryAquisicao.createAllFromJson(jsonAquisicao);
                    repositoryClassificacao.createAllFromJson(jsonClassificacao);
                    repositoryConvenio.createAllFromJson(jsonConvenio);
                    repositorySituacao.createAllFromJson(jsonSituacao);
                    repositoryDepartamento.createAllFromJson(jsonDepartamento);
                    repositoryBemtipo.createAllFromJson(jsonBemtipos);


                    listener.onSuccess(responseString);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });
    }
}
