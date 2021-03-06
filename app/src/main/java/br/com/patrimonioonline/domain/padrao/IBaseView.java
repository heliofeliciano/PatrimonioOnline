package br.com.patrimonioonline.domain.padrao;

import android.content.Intent;

import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;
import br.com.patrimonioonline.domain.models.entities.UsuarioEntity;
import br.com.patrimonioonline.domain.models.readonly.UsuarioReadonly;

/**
 * Created by helio on 28/06/16.
 */

public interface IBaseView {

    Boolean verificarSeUsuarioLogado();
    Boolean verificarSincronizacao();
    DepartamentoEntity getDepartamentoAtual();
    UsuarioEntity getUsuarioLogado();
    void logout();
    void logoutSucesso();

    void showToast(String msg);
    void navegarParaProximaActivity(Intent intent);
}
