package br.com.patrimonioonline.domain.bem;

import java.util.List;

import br.com.patrimonioonline.domain.models.entities.BemEntity;
import br.com.patrimonioonline.domain.models.entities.BemTipoEntity;
import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;
import io.realm.RealmResults;

/**
 * Created by helio on 27/06/16.
 */

public interface IBemListaView {

    // View
    void onBuscaDepartamentoSucesso(List<DepartamentoEntity> departamentoEntities);
    void onListaBensPorDepartamento(RealmResults<BemEntity> lista);
    void onAtualizarListaBens(RealmResults<BemEntity> lista);
    void onListaBensVazia();
    void setorEscolhido();
    void setorNaoEscolhido();
    void logout();
    void onExibirListaDepartamentos(List<DepartamentoEntity> departamentoEntities);

    void onExibirTiposBens(List<BemTipoEntity> bemTipoEntities);
    void irParaActivityAdicionarBem(BemTipoEntity bemTipoEntity);
    void exibirDadosQrCode(BemEntity bemEntity);

    // Interactor
    void verificarSeSetorJaFoiEscolhido();
}
