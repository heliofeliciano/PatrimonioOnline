package br.com.patrimonioonline.domain.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;

import br.com.patrimonioonline.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import pl.tajchert.nammu.Nammu;
import pl.tajchert.nammu.PermissionCallback;

import static br.com.patrimonioonline.R.id.ivBemCadastrarImagem1;

/**
 * Created by helio on 02/07/16.
 */

public class BemCadastrarImagensActivity extends BaseActivity {

    @BindView(ivBemCadastrarImagem1)
    ImageView imageView;

    String idBem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bem_cadastrar_imagens);

        ButterKnife.bind(this);

        init();

    }

    private void init() {
        idBem = getIntent().getExtras().getString("IdBem");
    }

    @OnClick(R.id.vwBemCadastrarAdicionarImagem)
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

    @OnClick(R.id.btnClickImagemGaleria)
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

    private void imagemRetornada(File imageFile) {

        Glide
                .with(this)
                .load(imageFile)
                .centerCrop()
                .crossFade()
                .error(R.drawable.image_error)
                .into(imageView);

    }
}
