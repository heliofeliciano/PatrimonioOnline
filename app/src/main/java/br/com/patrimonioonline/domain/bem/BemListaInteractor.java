package br.com.patrimonioonline.domain.bem;

import android.content.Context;

import java.util.List;

import br.com.patrimonioonline.domain.consts.DepartamentoPreferenceConst;
import br.com.patrimonioonline.domain.models.entities.BemTipoEntity;
import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;
import br.com.patrimonioonline.domain.models.entities.UsuarioEntity;
import br.com.patrimonioonline.domain.models.readonly.DepartamentoReadonly;
import br.com.patrimonioonline.domain.repos.Repository;
import br.com.patrimonioonline.lib.StoredPreference;

/**
 * Created by helio on 27/06/16.
 */

public class BemListaInteractor implements IBemListaInteractor {

    @Override
    public void buscarBensPorDepartamento() {

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
        _pref.limparObjeto(new DepartamentoReadonly());
        DepartamentoReadonly departamentoReadonly = (DepartamentoReadonly) _pref.buscarObjeto(new DepartamentoReadonly());

        if (departamentoReadonly != null) {
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
        _pref.salvarObjeto(new DepartamentoReadonly(departamentoEntity.id, departamentoEntity.descricao));


        /*StoredPreference _pref = new StoredPreference(context, DepartamentoPreferenceConst.DEPARTAMENTO_PREF);
        //_pref.limparObjeto(departamentoEntity);

        DepartamentoReadonly departamentoReadonly = new DepartamentoReadonly();
        departamentoReadonly.id = departamentoEntity.id;
        departamentoReadonly.descricao = departamentoEntity.descricao;
        departamentoReadonly.nomeresponsavel = departamentoEntity.nomeresponsavel;
        departamentoReadonly.emailresponsavel = departamentoEntity.emailresponsavel;
        departamentoReadonly.limite = departamentoEntity.limite;
        departamentoReadonly.telefone = departamentoEntity.telefone;
        departamentoReadonly.email = departamentoEntity.email;
        departamentoReadonly.fax = departamentoEntity.fax;
        departamentoReadonly.ramal = departamentoEntity.ramal;
        departamentoReadonly.instituicao = departamentoEntity.instituicao;

        _pref.salvarObjeto(departamentoReadonly);*/
    }
}
