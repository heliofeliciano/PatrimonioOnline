package br.com.patrimonioonline.domain.padrao;

import android.content.Context;

import br.com.patrimonioonline.domain.consts.UsuarioPreferenceConst;
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

        StoredPreference _pref = new StoredPreference(context, UsuarioPreferenceConst.USUARIO_PREF);
        UsuarioReadonly usuarioReadonly = (UsuarioReadonly) _pref.buscarObjeto(new UsuarioReadonly());

        return (usuarioReadonly != null);
    }
}
