package br.com.patrimonioonline.domain.bem;

import android.content.Context;

import java.util.List;

import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;

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
    public void onBuscarDepartamentosPorUsuario(List<DepartamentoEntity> departamentoEntities) {
        view.onBuscaDepartamentoSucesso(departamentoEntities);
    }

    @Override
    public void verificarSeSetorJaEscolhido() {
        interactor.verificarSeSetorJaEscolhido(context, this);
    }

    @Override
    public void buscarDepartamentosPorUsuario() {
        interactor.buscarDepartamentosPorUsuario(this);
    }

    @Override
    public void setorEscolhido() {
        view.setorEscolhido();
    }

    @Override
    public void setorNaoEscolhido() {
        view.setorNaoEscolhido();
    }

    @Override
    public void salvarEscolhaDepartamento(Context context, DepartamentoEntity departamentoEntity) {
        interactor.salvarPreferenceDepartamento(context, departamentoEntity);
    }
}