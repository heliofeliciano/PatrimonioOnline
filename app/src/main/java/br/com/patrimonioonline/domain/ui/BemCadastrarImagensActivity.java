package br.com.patrimonioonline.domain.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

import br.com.patrimonioonline.R;
import br.com.patrimonioonline.domain.adapter.RealmRecyclerViewBemListaImagensAdapter;
import br.com.patrimonioonline.domain.bem.BemImagemInteractor;
import br.com.patrimonioonline.domain.bem.IBemImagemPresenter;
import br.com.patrimonioonline.domain.models.entities.BemImagensEntity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import io.realm.RealmResults;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import pl.tajchert.nammu.Nammu;
import pl.tajchert.nammu.PermissionCallback;

/**
 * Created by helio on 02/07/16.
 */

public class BemCadastrarImagensActivity extends BaseActivity implements IBemImagemPresenter {

    @BindView(R.id.lvListaBemImagens)
    RealmRecyclerView lvListaBemImagens;

    RealmRecyclerViewBemListaImagensAdapter adapter;

    int idBem;
    BemImagemInteractor interactor;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bem_lista_imagens);

        ButterKnife.bind(this);

        if (toolbar != null)
        {
            setSupportActionBar(toolbar);
        }

        init();

    }

    private void init() {

        String strIdBem = getIntent().getExtras().getString("IdBem");
        idBem = Integer.valueOf(strIdBem);
        interactor = new BemImagemInteractor();
        interactor.buscarImagens(this, idBem);

        /*BemImagensEntity _imagemEntity = new BemImagensEntity();
        _imagemEntity.id = 1;
        _imagemEntity.descricao = "bla bla";
        _imagemEntity.caminho = "bla bla";
        List<BemImagensEntity> lista = new ArrayList<>();
        lista.add(_imagemEntity);*/

        //adapter = new RealmRecyclerViewBemListaImagensAdapter(this, lista, true, true);
    }

    @OnClick(R.id.btnBemImageCamera)
    public void btnTirarFoto(){

        int verificarPermissao = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (verificarPermissao == PackageManager.PERMISSION_GRANTED) {
            EasyImage.openCamera(this, 0);
        } else {
            Nammu.askForPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, new PermissionCallback() {
                @Override
                public void permissionGranted() {
                    EasyImage.openCamera(BemCadastrarImagensActivity.this, 0);
                }

                @Override
                public void permissionRefused() {

                }
            });
        }

        Toast.makeText(this, "Tirar foto", Toast.LENGTH_LONG).show();

    }

    @OnClick(R.id.btnBemImagemGaleria)
    public void btnFotoGaleria(){
        EasyImage.openGallery(this, 0);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Nammu.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                //Some error handling
            }

            @Override
            public void onImagePicked(File file, EasyImage.ImageSource imageSource, int i) {

                imagemRetornada(file);
            }

            @Override
            public void onCanceled(EasyImage.ImageSource source, int type) {
                //Cancel handling, you might wanna remove taken photo if it was canceled
                if (source == EasyImage.ImageSource.CAMERA) {
                    File file = EasyImage.lastlyTakenButCanceledPhoto(BemCadastrarImagensActivity.this);
                    if (file != null) {
                        file.delete();
                    }
                }
            }
        });
    }

    @Override
    public void onSalvarImagem() {

        Log.d("Imagem", "Imagem salva com sucesso");

    }

    @Override
    public void onBuscarImagem(RealmResults<BemImagensEntity> imagensEntities) {

        adapter = new RealmRecyclerViewBemListaImagensAdapter(this, imagensEntities, true, true);
        lvListaBemImagens.setAdapter(adapter);

        /*if (bemEntity.getListaBemImageEntities() != null &&
                bemEntity.getListaBemImageEntities().size() != 0) {
            Glide
                    .with(this)
                    .load(bemEntity.getListaBemImageEntities().get(0).getCaminho())
                    .centerCrop()
                    .crossFade()
                    .error(R.drawable.image_error)
                    .into(imageView);
        }*/

    }

    private void imagemRetornada(File imageFile) {

        interactor.salvarImagem(this, idBem, "teste imagem nome", imageFile.getAbsolutePath());


        /*Glide
                .with(this)
                .load(imageFile)
                .centerCrop()
                .crossFade()
                .error(R.drawable.image_error)
                .into(imageView);*/

    }
}
