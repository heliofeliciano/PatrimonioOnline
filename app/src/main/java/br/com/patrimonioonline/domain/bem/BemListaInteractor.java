package br.com.patrimonioonline.domain.bem;

import android.content.Context;

import java.util.List;

import br.com.patrimonioonline.domain.consts.DepartamentoPreferenceConst;
import br.com.patrimonioonline.domain.models.entities.BemEntity;
import br.com.patrimonioonline.domain.models.entities.BemTipoEntity;
import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;
import br.com.patrimonioonline.domain.models.entities.UsuarioEntity;
import br.com.patrimonioonline.domain.repos.Repository;
import br.com.patrimonioonline.lib.StoredPreference;
import io.realm.RealmResults;

/**
 * Created by helio on 27/06/16.
 */

public class BemListaInteractor implements IBemListaInteractor {

    @Override
    public void buscarBensPorDepartamento(IBemListaPresenter listener) {
        Repository<BemEntity> bemEntityRepository = new Repository<>(BemEntity.class);
        RealmResults<BemEntity> lista = bemEntityRepository.allResults();

        if (lista.size() == 0) {
            listener.onListaBensVazia();
        } else {
            listener.onListaBensPorDepartamento(lista);
        }

    }

    @Override
    public void atualizarListaBens(IBemListaPresenter listener) {

        Repository<BemEntity> bemEntityRepository = new Repository<>(BemEntity.class);
        RealmResults<BemEntity> lista = bemEntityRepository.allResults();

        listener.onAtualizarListaBens(lista);
    }

    @Override
    public void buscarDepartamentosPorUsuario(IBemListaPresenter listener) {

        Repository<UsuarioEntity> usuarioEntityRepository = new Repository<>(UsuarioEntity.class);
        List<DepartamentoEntity> departamentoEntities = usuarioEntityRepository.getByLogin("dbseller").departamentos;

        listener.onBuscarDepartamentosPorUsuario(departamentoEntities);
    }

    @Override
    public void verificarSeSetorJaEscolhido(Context context, IBemListaPresenter listener) {

        StoredPreference _pref = new StoredPreference(context, DepartamentoPreferenceConst.DEPARTAMENTO_PREF);
        //_pref.limparObjeto(new DepartamentoReadonly());
        DepartamentoEntity departamentoEntity = (DepartamentoEntity) _pref.buscarObjeto(new DepartamentoEntity());

        if (departamentoEntity != null) {
            listener.setorEscolhido();
        } else {
            listener.setorNaoEscolhido();
        }

    }

    @Override
    public void buscarBemTipo(Context context, IBemListaPresenter listener) {
        Repository<BemTipoEntity> bemTipoEntityRepository = new Repository<>(BemTipoEntity.class);
        List<BemTipoEntity> bemTipoEntities = bemTipoEntityRepository.all();

        listener.onBuscarBemTipo(bemTipoEntities);
    }

    @Override
    public void salvarPreferenceDepartamento(Context context, DepartamentoEntity departamentoEntity) {

        StoredPreference _pref = new StoredPreference(context, DepartamentoPreferenceConst.DEPARTAMENTO_PREF);
        _pref.salvarObjetoRealm(new DepartamentoEntity(), departamentoEntity.converterParaJson());
        //_pref.salvarObjeto(departamentoEntity.converterParaJson());

    }
}
