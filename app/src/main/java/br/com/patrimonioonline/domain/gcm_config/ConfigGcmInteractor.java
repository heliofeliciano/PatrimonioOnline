package br.com.patrimonioonline.domain.gcm_config;

import android.content.Context;

/**
 * Created by helio on 25/07/16.
 */

public class ConfigGcmInteractor implements IConfigGcmInteractor {

    @Override
    public void salvarRegIdNoGoogle(IConfigGcm listener, Context context, String googleProjectId) {
        listener.cadastrarRegIdNoGoogleResult(true, "ok");
    }
}
