package br.com.patrimonioonline.domain.departamento;

import android.content.Context;

import java.util.List;

import br.com.patrimonioonline.domain.consts.PreferenceConst;
import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;
import br.com.patrimonioonline.domain.models.entities.UsuarioEntity;
import br.com.patrimonioonline.domain.repos.RepositorioUsuario;
import br.com.patrimonioonline.domain.repos.Repository;
import br.com.patrimonioonline.domain.repos.RepositoryDepartamento;
import br.com.patrimonioonline.lib.StoredPreference;

/**
 * Created by helio on 13/07/16.
 */

public class DepartamentoInteractor implements IDepartamentoInteractor {

    @Override
    public void SalvarDepartamentoAtual(Context context, DepartamentoEntity departamentoEntity, IDepartamentoPresenter listener) {

        RepositorioUsuario _repositorio = new RepositorioUsuario();
        StoredPreference loginPreference = new StoredPreference(context, PreferenceConst.PREFERENCES);

        UsuarioEntity usuarioEntity = _repositorio.getByLogin(loginPreference.buscar(PreferenceConst.PrefUsuarioLogin));

        _repositorio.atualizarDepartamentoAtual(usuarioEntity, departamentoEntity);

        listener.AlterarDepartamentoResult();
    }

    @Override
    public void ListarDepartamentos(Context ctx, IDepartamentoPresenter listener) {

        Repository<UsuarioEntity> usuarioEntityRepository = new Repository<>(UsuarioEntity.class);

        StoredPreference _pref = new StoredPreference(ctx, PreferenceConst.PREFERENCES);
        String _login = _pref.buscar(PreferenceConst.PrefUsuarioLogin);

        List<DepartamentoEntity> departamentoEntities = usuarioEntityRepository.getByLogin(_login).departamentos;

        listener.listaDepartamentosSucesso(departamentoEntities);

    }
}
