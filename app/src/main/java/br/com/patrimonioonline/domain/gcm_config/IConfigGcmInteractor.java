package br.com.patrimonioonline.domain.gcm_config;

import android.content.Context;

/**
 * Created by helio on 25/07/16.
 */

public interface IConfigGcmInteractor {

    void salvarRegIdNoGoogle(IConfigGcm listener, Context context, String googleProjectId);

}
