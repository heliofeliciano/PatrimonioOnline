package br.com.patrimonioonline.domain.gcm_config;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.gcm.GcmListenerService;

import br.com.patrimonioonline.R;
import br.com.patrimonioonline.domain.ui.BemListaActivity;

/**
 * Created by helio on 20/07/16.
 */

public class GCMPushReceiverService extends GcmListenerService {

    @Override
    public void onMessageReceived(String from, Bundle data) {

        // Sempre que receber uma mensagem, passará por aqui!!!

        String mensagem = data.getString("message");
        sendNotification(mensagem);

    }

    private void sendNotification(String mensagem) {

        Intent intent = new Intent(this, BemListaActivity.class);
        intent.putExtra("mensagem", mensagem);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        int _requestCode = 0;
        PendingIntent _pendingIntent = PendingIntent.getActivity(this, _requestCode, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder noBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.logo_ecidade)
                .setContentTitle("Aprovado seu pedido")
                .setContentText(mensagem)
                .setAutoCancel(true)
                .setContentIntent(_pendingIntent);

        NotificationManager _notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        _notificationManager.notify(0, noBuilder.build());

    }
}
