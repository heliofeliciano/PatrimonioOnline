package br.com.patrimonioonline.domain.adapter.SpinnerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.patrimonioonline.R;
import br.com.patrimonioonline.domain.models.entities.AquisicaoEntity;

/**
 * Created by helio on 24/06/16.
 */

public class AquisicaoAdapter extends ArrayAdapter<AquisicaoEntity> {

    private Context context;
    private List<AquisicaoEntity> lista;

    public AquisicaoAdapter(Context context, int textViewResourceId, List<AquisicaoEntity> lista) {
        super(context, textViewResourceId, lista);

        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public AquisicaoEntity getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        View view;

        if (convertView != null) {
            view = convertView;
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.spinner_item, parent, false);
            holder = new ViewHolder(view);
        }

        holder.tvSpinnerItem.setText(lista.get(position).toString());

        return view;
    }

    class ViewHolder {

        TextView tvSpinnerItem;

        public ViewHolder(View view) {
            tvSpinnerItem = (TextView) view.findViewById(R.id.tv_spinner_item);
        }
    }
}
