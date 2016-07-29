package br.com.patrimonioonline.domain.gcm_config;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by helio on 19/07/16.
 */

public class GCMTokenRefreshListenerService extends InstanceIDListenerService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        Intent intent = new Intent(this, GCMRegistrationIntentService.class);
        startService(intent);
    }
}
