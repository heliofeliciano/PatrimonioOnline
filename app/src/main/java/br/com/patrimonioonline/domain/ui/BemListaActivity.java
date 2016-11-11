package br.com.patrimonioonline.domain.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import br.com.patrimonioonline.R;
import br.com.patrimonioonline.domain.adapter.RealmRecyclerViewBemListaAdapter;
import br.com.patrimonioonline.domain.bem.BemListaPresenter;
import br.com.patrimonioonline.domain.bem.IBemListaView;
import br.com.patrimonioonline.domain.gcm_config.GCMRegistrationIntentService;
import br.com.patrimonioonline.domain.models.entities.BemEntity;
import br.com.patrimonioonline.domain.models.entities.BemTipoEntity;
import br.com.patrimonioonline.domain.models.entities.DepartamentoEntity;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import io.realm.RealmResults;

/**
 * Created by helio on 18/06/16.
 */

public class BemListaActivity extends BaseActivity implements IBemListaView {

    private BroadcastReceiver _registrationBroadcastReceiver;

    public static final int REQUEST_PLAY_SERVICES = 1;
    public static String codigoQrCode;

    private static final int RC_BARCODE_CAPTURE = 9001;
    /*@BindView(R.id.lvListaBens)
    ListView lvLista;*/

    @BindView(R.id.rvRealmBemLista)
    RealmRecyclerView rvRealmBemLista;

    RealmRecyclerViewBemListaAdapter adapter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    BemListaPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bem_lista_recycler);

        ButterKnife.bind(this);

        if (toolbar != null)
        {
            setSupportActionBar(toolbar);
        }

        init();
        inicializarBroadcastReceiver();
        checarGooglePlayService();
    }

    @Override
    protected void onResume() {
        super.onResume();

        initResume();
    }

    private void init() {


        if (getIntent().getExtras() != null) {
            Toast.makeText(this, getIntent().getExtras().getString("mensagem"), Toast.LENGTH_SHORT).show();
        }

    }

    private void initResume() {

        LocalBroadcastManager.getInstance(this).registerReceiver(_registrationBroadcastReceiver,
                new IntentFilter(GCMRegistrationIntentService.REGISTRATION_SUCCESS));
        LocalBroadcastManager.getInstance(this).registerReceiver(_registrationBroadcastReceiver,
                new IntentFilter(GCMRegistrationIntentService.REGISTRATION_ERROR));

        presenter = new BemListaPresenter(getApplicationContext(), this);
        presenter.buscarListaBens();
    }

    private void inicializarBroadcastReceiver() {
        _registrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(GCMRegistrationIntentService.REGISTRATION_SUCCESS)){
                    String token = intent.getStringExtra("token");
                    //Toast.makeText(getApplicationContext(), "Registration token " + token, Toast.LENGTH_SHORT).show();
                } else if (intent.getAction().equals(GCMRegistrationIntentService.REGISTRATION_ERROR)){
                    Toast.makeText(getApplicationContext(), "GCM Registration erro", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Ocorreu um erro", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(_registrationBroadcastReceiver);
    }

    @Override
    public void onListaBensPorDepartamento(RealmResults<BemEntity> lista) {

        adapter = new RealmRecyclerViewBemListaAdapter(this, lista, true, true);
        rvRealmBemLista.setAdapter(adapter);

        // Atualizar lista
        rvRealmBemLista.setOnRefreshListener(new RealmRecyclerView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.atualizarListaBens();
            }
        });
    }

    @Override
    public void onAtualizarListaBens(RealmResults<BemEntity> lista) {

        rvRealmBemLista.setRefreshing(false);

    }

    @Override
    public void onListaBensVazia() {

        Toast.makeText(this, "Não há bens cadastrados. Clique no + para adicionar um bem", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onExibirTiposBens(final List<BemTipoEntity> bemTipoEntities) {
        new MaterialDialog.Builder(this)
                .title(R.string.str_escolher_bem_tipo)
                .items(bemTipoEntities)
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice(){
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        irParaActivityAdicionarBem(bemTipoEntities.get(dialog.getSelectedIndex()));
                        return true;
                    }
                })
                .positiveText(R.string.str_escolher)
                .show();
    }

    @Override
    public void irParaActivityAdicionarBem(BemTipoEntity bemTipoEntity) {

        Intent _intent = new Intent(this, BemCadastrarActivity.class);
        // TODO: 11/9/16 checar a conversao para json
        /*_intent.putExtra("BemTipo", bemTipoEntity.converterParaJson());
        if (codigoQrCode != null) {
            _intent.putExtra("codigoQrCode", codigoQrCode);
        }

        startActivity(_intent);*/
    }

    @Override
    public void exibirDadosQrCode(BemEntity bemEntity) {

        Toast.makeText(this, bemEntity.getDescricao(), Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_adicionar_bem:
                //startActivity(new Intent(this, BemCadastrarActivity.class));
                presenter.buscarBemTipos();
                return true;
            case R.id.action_alterar_departamento:
                startActivity(new Intent(this, AlterarDepartamentoActivity.class));
                return true;
            case R.id.action_ler_barcode:
                Intent intent = new Intent(this, BemBarcodeCaptureActivity.class);
                startActivityForResult(intent, RC_BARCODE_CAPTURE);
                return true;
            case R.id.action_solicitar_placas:
                Intent intentSolicitarPlacas = new Intent(this, SolicitarPlacasAcitivity.class);
                startActivity(intentSolicitarPlacas);
                return true;
            case R.id.action_logout:
                this.logout();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_BARCODE_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BemBarcodeCaptureActivity.BarcodeObject);
                    //Toast.makeText(this, barcode.displayValue, Toast.LENGTH_LONG).show();
                    codigoQrCode = barcode.displayValue;
                    presenter.buscarBemTipos();
                } else {
                    Toast.makeText(this, "Barcode não foi capturado", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, String.format("Erro na leitura",CommonStatusCodes.getStatusCodeString(resultCode)), Toast
                        .LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_PLAY_SERVICES) {
            checarGooglePlayService();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    private void checarGooglePlayService() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        if (resultCode != ConnectionResult.SUCCESS) {

            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {

                GooglePlayServicesUtil.showErrorNotification(resultCode, getApplicationContext());

            } else {
                Toast.makeText(this, R.string.gcm_naosuportado, Toast.LENGTH_SHORT).show();
                finish();
            }

        } else {

            Intent it = new Intent(this, GCMRegistrationIntentService.class);
            startService(it);

        }
    }

}
