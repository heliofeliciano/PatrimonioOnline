package br.com.patrimonioonline.domain.gcm_config;

/**
 * Created by helio on 25/07/16.
 */

public interface IConfigGcm {

    void cadastrarRegIdNoGoogle();
    void cadastrarRegIdNoGoogleResult(Boolean result, String msg);


}
