package br.com.patrimonioonline.domain.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.patrimonioonline.R;
import br.com.patrimonioonline.domain.bem.IMapsPresenter;
import br.com.patrimonioonline.domain.bem.MapsInteractor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.tajchert.nammu.Nammu;
import pl.tajchert.nammu.PermissionCallback;

/**
 * Created by helio on 11/07/16.
 */
public class MapsActivity extends BaseActivity
        implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener,
        IMapsPresenter {

    @BindView(R.id.btnLocalizacaoCancelar)
    Button btnLocalizacaoCancelar;

    private MapsInteractor _interactor;
    private int _idBem;

    private LocationRequest _locationRequest;
    private GoogleMap _map;
    private SupportMapFragment _mapFragment;
    private GoogleApiClient _googleApiClient;
    Location _lastLocation;
    Marker _currentLocationMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bem_mapa);

        _interactor = new MapsInteractor();
        ButterKnife.bind(this);

        init();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void init() {

        String strIdBem = getIntent().getExtras().getString("IdBem");
        _idBem = Integer.valueOf(strIdBem);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

        if (_googleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(_googleApiClient, this);
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {

        _map = map;
        _map.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        // Inicializar GoogleConst Play Service
        int verificarPermissao = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (verificarPermissao != PackageManager.PERMISSION_GRANTED) {
            Nammu.askForPermission(this, Manifest.permission.ACCESS_FINE_LOCATION, new PermissionCallback() {
                @Override
                public void permissionGranted() {
                    configurarMapa();
                }

                @Override
                public void permissionRefused() {

                }
            });
        } else {
            configurarMapa();
        }

    }

    private void configurarMapa() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            if (_googleApiClient == null) {
                buildGoogleApiClient();
            }
            _map.setMyLocationEnabled(true);

            _map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(LatLng latLng) {

                    if (_currentLocationMarker != null) {
                        _currentLocationMarker.remove();
                    }

                    MarkerOptions _markerOptions = new MarkerOptions();
                    _markerOptions.position(latLng);
                    _markerOptions.title("Posição Atual");
                    _markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                    _currentLocationMarker = _map.addMarker(_markerOptions);

                    _map.moveCamera(CameraUpdateFactory.newLatLng(latLng));

                }
            });
        }

    }

    protected synchronized void buildGoogleApiClient() {
        _googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        _googleApiClient.connect();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Nammu.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onLocationChanged(Location location) {
        _lastLocation = location;

        // Caso o Marker nao seja nulo, remover.
        if (_currentLocationMarker != null) {
            _currentLocationMarker.remove();
        }

        // Local do lugar atual
        LatLng _latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions _markerOptions = new MarkerOptions();
        _markerOptions.position(_latLng);
        _markerOptions.title("Posição Atual");
        _markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        _currentLocationMarker = _map.addMarker(_markerOptions);

        _map.moveCamera(CameraUpdateFactory.newLatLng(_latLng));
        _map.animateCamera(CameraUpdateFactory.zoomTo(20));

        if (_googleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(_googleApiClient, this);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        _locationRequest = new LocationRequest();
        _locationRequest.setInterval(1000);
        _locationRequest.setFastestInterval(1000);
        _locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(_googleApiClient, _locationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {}

    @Override
    @OnClick(R.id.btnLocalizacaoSalvar)
    public void salvar() {
        _interactor.Salvar(_idBem, String.valueOf(_currentLocationMarker.getPosition().latitude),
                String.valueOf(_currentLocationMarker.getPosition().longitude),this);
    }

    @Override
    public void salvarResult() {

    }
}
