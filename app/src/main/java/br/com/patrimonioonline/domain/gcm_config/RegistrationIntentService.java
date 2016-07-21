package br.com.patrimonioonline.domain.gcm_config;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

import br.com.patrimonioonline.R;


/**
 * Created by helio on 19/07/16.
 */
public class RegistrationIntentService extends IntentService {

    private static final String REGISTRATION_SUCCESS = "RegistrationSuccess";
    private static final String REGISTRATION_ERROR = "RegistrationError";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public RegistrationIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        RegistrarCGM();
    }

    private void RegistrarCGM() {

        Intent registrationComplete = null;
        String token = null;

        try {

            InstanceID instanceID = InstanceID.getInstance(this);

            token = instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            Log.w("CGMRegIntentService", token);

            registrationComplete = new Intent(REGISTRATION_SUCCESS);
            registrationComplete.putExtra("token", token);
        } catch (IOException e) {
            Log.w("CGMRegIntentService", "RegistrationError");
            registrationComplete = new Intent(REGISTRATION_SUCCESS);
        }

        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

}
