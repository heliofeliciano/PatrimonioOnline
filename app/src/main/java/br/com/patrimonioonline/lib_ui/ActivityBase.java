package br.com.patrimonioonline.lib_ui;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by helio on 16/06/16.
 */

public class ActivityBase extends AppCompatActivity implements IActivityBase {

    public ActivityBase() {
        super();
    }


    @Override
    public void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAlert(String msg) {

    }
}
