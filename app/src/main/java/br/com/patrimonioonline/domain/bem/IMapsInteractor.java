package br.com.patrimonioonline.domain.bem;

/**
 * Created by helio on 13/07/16.
 */
public interface IMapsInteractor {

    void Salvar(int idBem, String logitude, String latitude, IMapsPresenter listener);

}
