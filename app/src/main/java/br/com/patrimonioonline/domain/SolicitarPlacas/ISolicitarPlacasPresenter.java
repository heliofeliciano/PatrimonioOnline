package br.com.patrimonioonline.domain.SolicitarPlacas;

/**
 * Created by helio on 20/07/16.
 */
public interface ISolicitarPlacasPresenter {

    void Salvar() throws Exception;
    void Cancelar();
    void SalvarResult(String mensagem);
    void Error(String mensagem);

}
