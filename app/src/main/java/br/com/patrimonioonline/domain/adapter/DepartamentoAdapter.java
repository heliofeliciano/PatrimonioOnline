package br.com.patrimonioonline.domain.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.patrimonioonline.R;
import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;

/**
 * Created by helio on 11/9/16.
 */

public class DepartamentoAdapter extends ArrayAdapter<DepartamentoEntity> {

    Context context;
    int layoutResourceId;
    List<DepartamentoEntity> listaDepartamentos = null;

    public DepartamentoAdapter(Context context, int resource, List<DepartamentoEntity> listaObjetos) {
        super(context, resource, listaObjetos);
        this.layoutResourceId = resource;
        this.context = context;
        this.listaDepartamentos = listaObjetos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        HeaderHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new HeaderHolder();
            holder.tvDepartamentoItemDescricao = (TextView) row.findViewById(R.id.tvDepartamentoItemDescricao);

            row.setTag(holder);
        }
        else
        {
            holder = (HeaderHolder) row.getTag();
        }

        DepartamentoEntity item = listaDepartamentos.get(position);
        holder.tvDepartamentoItemDescricao.setText(item.toStringComCodigo());

        return row;
    }

    private class HeaderHolder {
        public TextView tvDepartamentoItemDescricao;
    }
}
