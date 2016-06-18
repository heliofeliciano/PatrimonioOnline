package br.com.patrimonioonline.domain.modelshttp;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by helio on 17/06/16.
 */

public class MinhaAsyncTask extends AsyncTask<String, Void, String> {

    private Context context;

    public MinhaAsyncTask(Context context) {
        this.context = context;

    }

    @Override
    protected String doInBackground(String... params) {
        return null;
    }
}
