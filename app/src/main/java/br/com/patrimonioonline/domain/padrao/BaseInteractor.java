package br.com.patrimonioonline.domain.padrao;

import android.content.Context;

import br.com.patrimonioonline.domain.consts.PreferenceConst;
import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;
import br.com.patrimonioonline.domain.models.entities.UsuarioEntity;
import br.com.patrimonioonline.domain.models.readonly.UsuarioReadonly;
import br.com.patrimonioonline.domain.repos.RepositorioUsuario;
import br.com.patrimonioonline.domain.repos.Repository;
import br.com.patrimonioonline.domain.repos.RepositoryDepartamento;
import br.com.patrimonioonline.lib.StoredPreference;

/**
 * Created by helio on 28/06/16.
 */

public class BaseInteractor implements IBaseInteractor {

    private Context context;

    public BaseInteractor(Context context) {
        this.context = context;
    }

    @Override
    public Boolean verificarSeUsuarioLogado() {

        StoredPreference _pref = new StoredPreference(context, PreferenceConst.PREFERENCES);
        String _usuarioLogin = _pref.buscar(PreferenceConst.PrefUsuarioLogin);

        return (_usuarioLogin != null);
    }

    @Override
    public DepartamentoEntity buscarDepartamentoAtual() {

        RepositorioUsuario repositorioUsuario = new RepositorioUsuario();
        DepartamentoEntity _departamentoAtual = null;

        UsuarioEntity _usuarioEntity = repositorioUsuario.getByLogin(getUsuarioLogado().login);
        if (_usuarioEntity != null) {
            _departamentoAtual = repositorioUsuario.getByLogin(getUsuarioLogado().login).departamentoAtual;
        }

        return _departamentoAtual;
    }

    @Override
    public UsuarioEntity getUsuarioLogado() {

        StoredPreference _pref = new StoredPreference(context, PreferenceConst.PREFERENCES);
        Repository<UsuarioEntity> repositorio = new Repository<>(UsuarioEntity.class);
        UsuarioEntity _usuarioEntity = repositorio.getByLogin(_pref.buscar(PreferenceConst.PrefUsuarioLogin));

        return _usuarioEntity;
    }

    @Override
    public void logout(IBasePresenter listener) {

        StoredPreference _pref = new StoredPreference(context, PreferenceConst.PREFERENCES);
        _pref.limparTodos();

        listener.logoutSucesso();
    }

    @Override
    public Boolean verificarSincronizacao() {
        StoredPreference _pref = new StoredPreference(context, PreferenceConst.PREFERENCES);
        DepartamentoEntity departamentoEntity = (DepartamentoEntity)
                _pref.buscarObjeto(new DepartamentoEntity(), PreferenceConst.PrefDepartamento);

        return (departamentoEntity != null);
    }
}
