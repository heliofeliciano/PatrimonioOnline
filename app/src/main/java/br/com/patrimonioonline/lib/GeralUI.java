package br.com.patrimonioonline.lib;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by helio on 11/10/16.
 */

public class GeralUI {

    private Context _ctx;

    public GeralUI(Context context) {
        this._ctx = context;
    }

    public void ShowToast(String msg) {

        Toast.makeText(_ctx, msg, Toast.LENGTH_LONG).show();

    }

}
