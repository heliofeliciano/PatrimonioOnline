package br.com.patrimonioonline.domain.bem;

import android.content.Context;

import java.util.List;

import br.com.patrimonioonline.domain.models.entities.BemEntity;
import br.com.patrimonioonline.domain.models.entities.BemTipoEntity;
import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;
import io.realm.RealmResults;

/**
 * Created by helio on 27/06/16.
 */

public interface IBemListaPresenter {

    // View
    void onBuscarBemTipo(List<BemTipoEntity> bemTipoEntities);
    void onListaBensVazia();
    void onListaBensPorDepartamento(RealmResults<BemEntity> lista);
    void onAtualizarListaBens(RealmResults<BemEntity> lista);

    // Interactor
    void buscarBemTipos();
    void buscarListaBens();
    void atualizarListaBens();
    void buscarDadosLidoPeloQrCode();
    void buscarDadosLidoPeloQrCodeResult(BemEntity bemEntity);
}
