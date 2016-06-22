package br.com.patrimonioonline.domain.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.patrimonioonline.domain.models.entities.BemTipoEntity;

/**
 * Created by helio on 21/06/16.
 */

public class BemtipoAdapter extends BaseAdapter {

    private Context context;
    private List<BemTipoEntity> lista;
    LayoutInflater inflater;

    public BemtipoAdapter(Context context, List<BemTipoEntity> lista) {
        this.context = context;
        this.lista = lista;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long) position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView tv_bemtipo = new TextView(context);

        return null;
    }
}
