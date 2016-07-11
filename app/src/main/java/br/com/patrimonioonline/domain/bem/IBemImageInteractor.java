package br.com.patrimonioonline.domain.bem;

/**
 * Created by helio on 09/07/16.
 */
public interface IBemImageInteractor {
    void salvarImagem(IBemImagemPresenter listener, int idBem, String descricao, String caminho);
}
