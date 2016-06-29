package br.com.patrimonioonline.domain.ui;

import android.app.Application;
import android.os.SystemClock;

import java.util.concurrent.TimeUnit;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by helio on 15/06/16.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        RealmConfiguration realmConfig = new RealmConfiguration.Builder(getApplicationContext()).build();
        Realm.setDefaultConfiguration(realmConfig);

        SystemClock.sleep(TimeUnit.SECONDS.toMillis(3));

    }
}
