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

public class BemListaPresenter implements IBemListaPresenter {

    private IBemListaView view;
    private BemListaInteractor interactor;
    private Context context;

    public BemListaPresenter(Context context, IBemListaView view) {
        this.view = view;
        this.context = context;
        this.interactor = new BemListaInteractor();
    }

    @Override
    public void buscarDadosLidoPeloQrCode() {

    }

    @Override
    public void buscarDadosLidoPeloQrCodeResult(BemEntity bemEntity) {
        view.exibirDadosQrCode(bemEntity);
    }

    @Override
    public void buscarBemTipos() {
        interactor.buscarBemTipo(context, this);
    }

    @Override
    public void buscarListaBens() {
        interactor.buscarBensPorDepartamento(this);
    }

    @Override
    public void atualizarListaBens() {
        interactor.atualizarListaBens(this);
    }

    @Override
    public void onListaBensVazia() {
        view.onListaBensVazia();
    }

    @Override
    public void onBuscarBemTipo(List<BemTipoEntity> bemTipoEntities) {
        view.onExibirTiposBens(bemTipoEntities);
    }

    @Override
    public void onListaBensPorDepartamento(RealmResults<BemEntity> lista) {
        view.onListaBensPorDepartamento(lista);
    }

    @Override
    public void onAtualizarListaBens(RealmResults<BemEntity> lista) {
        view.onAtualizarListaBens(lista);
    }

}
