package br.com.patrimonioonline.domain.configuracao;

import android.content.Context;

import java.util.List;

import br.com.patrimonioonline.domain.models.readonly.AquisicaoReadonly;
import br.com.patrimonioonline.domain.models.readonly.ClassificacaoReadonly;
import br.com.patrimonioonline.domain.models.readonly.ConvenioReadonly;
import br.com.patrimonioonline.domain.models.readonly.SituacaoReadonly;

/**
 * Created by helio on 15/06/16.
 */

public interface ISincronizacaoInteractor {

    void realizarSincronizacao(Context context, ISincronizacaoPresenter listener);

}
