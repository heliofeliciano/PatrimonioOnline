package br.com.patrimonioonline.domain.departamento;

import android.content.Context;

import java.util.List;

import br.com.patrimonioonline.domain.consts.PreferenceConst;
import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;
import br.com.patrimonioonline.domain.models.entities.UsuarioEntity;
import br.com.patrimonioonline.domain.repos.Repository;
import br.com.patrimonioonline.lib.StoredPreference;

/**
 * Created by helio on 13/07/16.
 */

public class DepartamentoInteractor implements IDepartamentoInteractor {

    @Override
    public void Salvar(Context context, DepartamentoEntity departamentoEntity, IDepartamentoPresenter listener) {

        StoredPreference _pref = new StoredPreference(context, PreferenceConst.PREFERENCES);
        // TODO: 11/9/16 checar a conversao para json
        //_pref.salvarObjetoRealm(new DepartamentoEntity(), departamentoEntity.converterParaJson());

        listener.AlterarDepartamentoResult();
    }

    @Override
    public void ListarDepartamentos(IDepartamentoPresenter listener) {

        Repository<UsuarioEntity> usuarioEntityRepository = new Repository<>(UsuarioEntity.class);
        List<DepartamentoEntity> departamentoEntities = usuarioEntityRepository.getByLogin("dbseller").departamentos;

        listener.ListarDepartamentosResult(departamentoEntities);

    }
}
