package br.com.patrimonioonline.domain.gcm_config;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

import br.com.patrimonioonline.R;
import br.com.patrimonioonline.domain.login.async.LoginAsyncInteractor;


/**
 * Created by helio on 19/07/16.
 */
public class GCMRegistrationIntentService extends IntentService {

    public static final String REGISTRATION_SUCCESS = "RegistrationSuccess";
    public static final String REGISTRATION_ERROR = "RegistrationError";

    public GCMRegistrationIntentService() {
        super("");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        RegistrarGCM();
    }

    private void RegistrarGCM() {

        Intent registrationComplete = null;
        String token = null;

        try {

            InstanceID instanceID = InstanceID.getInstance(getApplicationContext());

            token = instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            Log.w("CGMRegIntentService", token);

            registrationComplete = new Intent(REGISTRATION_SUCCESS);

            registrationComplete.putExtra("token", token);

            // TODO: 27/07/16 Neste momento deve salvar no servidor qual o token do usuário atual
            LoginAsyncInteractor interactor = new LoginAsyncInteractor();
            interactor.cadastrarRegIdDispositivo(getApplicationContext(), token);


        } catch (IOException e) {

            Log.w("CGMRegIntentService", "RegistrationError");
            registrationComplete = new Intent(REGISTRATION_ERROR);

        }

        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

}
