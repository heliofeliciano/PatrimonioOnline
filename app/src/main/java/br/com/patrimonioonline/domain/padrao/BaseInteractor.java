package br.com.patrimonioonline.domain.padrao;

import android.content.Context;

import br.com.patrimonioonline.domain.consts.PreferenceConst;
import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;
import br.com.patrimonioonline.domain.models.entities.UsuarioEntity;
import br.com.patrimonioonline.domain.models.readonly.UsuarioReadonly;
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
        //UsuarioReadonly usuarioReadonly = (UsuarioReadonly) _pref.buscarObjeto(new UsuarioReadonly());
        UsuarioEntity _usuarioEntity = (UsuarioEntity) _pref.buscarObjeto(new UsuarioEntity(), PreferenceConst.PrefUsuario);

        return (_usuarioEntity != null);
    }

    @Override
    public DepartamentoEntity buscarDepartamentoLogado() {

        StoredPreference _pref = new StoredPreference(context, PreferenceConst.PREFERENCES);
        DepartamentoEntity departamentoEntity = (DepartamentoEntity)
                _pref.buscarObjeto(new DepartamentoEntity(), PreferenceConst.PrefDepartamento);


        return departamentoEntity;
    }

    @Override
    public UsuarioEntity getUsuarioLogado() {

        StoredPreference _pref = new StoredPreference(context, PreferenceConst.PREFERENCES);
        //UsuarioReadonly usuarioReadonly = (UsuarioReadonly) _pref.buscarObjeto(new UsuarioReadonly());
        UsuarioEntity _usuarioEntity = (UsuarioEntity)
                _pref.buscarObjeto(new UsuarioEntity(), PreferenceConst.PrefUsuario);

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
