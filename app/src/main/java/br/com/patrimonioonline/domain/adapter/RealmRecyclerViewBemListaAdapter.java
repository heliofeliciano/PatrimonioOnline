package br.com.patrimonioonline.domain.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import br.com.patrimonioonline.R;
import br.com.patrimonioonline.domain.models.entities.BemEntity;
import br.com.patrimonioonline.domain.ui.BemCadastrarActivity;
import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;

/**
 * Created by helio on 30/06/16.
 */

public class RealmRecyclerViewBemListaAdapter extends RealmBasedRecyclerViewAdapter<BemEntity, RealmRecyclerViewBemListaAdapter.ViewHolder> {

    Context context;

    public RealmRecyclerViewBemListaAdapter(
            Context context,
            RealmResults<BemEntity> realmResults,
            boolean automaticUpdate,
            boolean animateIdType) {

        super(context, realmResults, automaticUpdate, animateIdType);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int i) {

        View v = inflater.inflate(R.layout.activity_bem_lista_item, viewGroup, false);
        return new ViewHolder((FrameLayout) v);
    }

    @Override
    public void onBindRealmViewHolder(ViewHolder viewHolder, int position) {
        final BemEntity _bemEntity = realmResults.get(position);
        viewHolder.descricao.setText(_bemEntity.getDescricao());
        viewHolder.situacao.setText(_bemEntity.getSituacaoEntity().getDescricao());
        viewHolder.setor.setText(_bemEntity.getDepartamentoEntity().descricao);

        if (_bemEntity.getListaBemImageEntities() != null && _bemEntity.getListaBemImageEntities().size() != 0) {

            String caminho = _bemEntity.getListaBemImageEntities().get(0).getCaminho();

            // Setar imagem do bem
            Glide
                    .with(context)
                    .load(caminho)
                    .centerCrop()
                    .crossFade()
                    .error(R.drawable.image_error)
                    .into(viewHolder.imagem);

        }

        if (_bemEntity.getClassificacaoEntity() != null) {
            viewHolder.classificacao.setText(_bemEntity.getClassificacaoEntity().descricao);
        }

        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent _intent = new Intent(context, BemCadastrarActivity.class);
                _intent.putExtra("IdBem", String.valueOf(_bemEntity.getId()));
                context.startActivity(_intent);
            }
        });
    }

    public class ViewHolder extends RealmViewHolder {

        public FrameLayout container;
        public TextView descricao;
        public TextView situacao;
        public TextView classificacao;
        public TextView setor;
        public CircleImageView imagem;

        public ViewHolder(FrameLayout container) {
            super(container);
            this.container = container;
            this.descricao = (TextView) container.findViewById(R.id.tvListaBemItemDescricao);
            this.situacao = (TextView) container.findViewById(R.id.tvListaBemItemSituacao);
            this.classificacao = (TextView) container.findViewById(R.id.tvListaBemItemClassificacao);
            this.setor = (TextView) container.findViewById(R.id.tvListaBemItemSetor);
            this.imagem = (CircleImageView) container.findViewById(R.id.cvListaBemImagem);
        }
    }

}
