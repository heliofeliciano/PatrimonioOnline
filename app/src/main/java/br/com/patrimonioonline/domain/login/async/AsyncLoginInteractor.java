package br.com.patrimonioonline.domain.login.async;

import android.os.Handler;

import br.com.patrimonioonline.domain.login.OnLoginFinishedListener;

/**
 * Created by helio on 12/06/16.
 */

public class AsyncLoginInteractor implements IAsyncLoginInteractor {

    public AsyncLoginInteractor() {
    }

    @Override
    public void validarLogin(final OnLoginFinishedListener listener, final String usuario, String senha) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (usuario.contains("dbseller")){
                    listener.onSuccess();
                } else {
                    listener.onError();
                }
            }
        }, 2000);
    }
}
