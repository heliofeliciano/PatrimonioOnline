package br.com.patrimonioonline.domain.login.async;

import cz.msebera.android.httpclient.Header;

/**
 * Created by helio on 13/06/16.
 */

public interface OnLoginAsync {

    void onAsyncStart();
    void onAsyncSuccess(String retorno);
    void onAsyncFailure(String retorno);
    void onAsyncRetry();

}
