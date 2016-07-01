package br.com.patrimonioonline.domain.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import br.com.patrimonioonline.R;
import br.com.patrimonioonline.domain.models.entities.BemEntity;
import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;

/**
 * Created by helio on 30/06/16.
 */

public class RecyclerViewBemListaAdapter extends RealmBasedRecyclerViewAdapter<BemEntity, RecyclerViewBemListaAdapter.ViewHolder> {

    public RecyclerViewBemListaAdapter(
            Context context,
            RealmResults<BemEntity> realmResults,
            boolean automaticUpdate,
            boolean animateIdType) {

        super(context, realmResults, automaticUpdate, animateIdType);
    }

    @Override
    public ViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int i) {

        View v = inflater.inflate(R.layout.activity_bem_lista_item, viewGroup, false);
        return new ViewHolder((FrameLayout) v);
    }

    @Override
    public void onBindRealmViewHolder(ViewHolder viewHolder, int position) {
        BemEntity _bemEntity = realmResults.get(position);
        viewHolder.descricao.setText(_bemEntity.descricao);
    }

    public class ViewHolder extends RealmViewHolder {

        public FrameLayout container;
        public TextView descricao;

        public ViewHolder(FrameLayout container) {
            super(container);
            this.container = container;
            this.descricao = (TextView) container.findViewById(R.id.tvListaBemItemDescricao);
        }
    }

    /*@Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_bem_lista_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvListaBemItemDescricao;

        public ViewHolder(View v) {
            super(v);

        }
    }*/

}
