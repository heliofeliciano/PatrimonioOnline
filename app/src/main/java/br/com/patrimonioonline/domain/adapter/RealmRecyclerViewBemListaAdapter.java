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

public class RealmRecyclerViewBemListaAdapter extends RealmBasedRecyclerViewAdapter<BemEntity, RealmRecyclerViewBemListaAdapter.ViewHolder> {

    public RealmRecyclerViewBemListaAdapter(
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
        viewHolder.situacao.setText(_bemEntity.situacaoEntity.descricao);
        viewHolder.setor.setText(_bemEntity.departamentoEntity.descricao);

        if (_bemEntity.classificacaoEntity != null) {
            viewHolder.classificacao.setText(_bemEntity.classificacaoEntity.descricao);
        }
    }

    public class ViewHolder extends RealmViewHolder {

        public FrameLayout container;
        public TextView descricao;
        public TextView situacao;
        public TextView classificacao;
        public TextView setor;

        public ViewHolder(FrameLayout container) {
            super(container);
            this.container = container;
            this.descricao = (TextView) container.findViewById(R.id.tvListaBemItemDescricao);
            this.situacao = (TextView) container.findViewById(R.id.tvListaBemItemSituacao);
            this.classificacao = (TextView) container.findViewById(R.id.tvListaBemItemClassificacao);
            this.setor = (TextView) container.findViewById(R.id.tvListaBemItemSetor);
        }
    }

}
