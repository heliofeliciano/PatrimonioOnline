package br.com.patrimonioonline.domain.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import br.com.patrimonioonline.R;
import br.com.patrimonioonline.domain.models.entities.BemImagensEntity;
import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;

/**
 * Created by helio on 30/06/16.
 */

public class RealmRecyclerViewBemListaImagensAdapter extends RealmBasedRecyclerViewAdapter<BemImagensEntity, RealmRecyclerViewBemListaImagensAdapter
        .ViewHolder> {

    Context context;

    public RealmRecyclerViewBemListaImagensAdapter(
            Context context,
            RealmResults<BemImagensEntity> realmResults,
            boolean automaticUpdate,
            boolean animateIdType) {

        super(context, realmResults, automaticUpdate, animateIdType);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int i) {

        View v = inflater.inflate(R.layout.activity_bem_lista_imagens_item, viewGroup, false);
        return new ViewHolder((LinearLayout) v);
    }

    @Override
    public void onBindRealmViewHolder(ViewHolder viewHolder, int position) {
        final BemImagensEntity _imagemEntity = realmResults.get(position);

        // Setar imagem do bem
        if (_imagemEntity != null) {
            Glide
                    .with(context)
                    .load(_imagemEntity.getCaminho())
                    .centerCrop()
                    .crossFade()
                    .error(R.drawable.image_error)
                    .into(viewHolder.imageView);
        }
    }

    public class ViewHolder extends RealmViewHolder {

        public LinearLayout container;
        public ImageView imageView;

        public ViewHolder(LinearLayout container) {
            super(container);
            this.container = container;
            this.imageView = (ImageView) container.findViewById(R.id.ivBemCadastrarImagem1);
        }
    }

}
