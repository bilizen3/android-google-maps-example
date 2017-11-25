package com.example.bill.examplegooglemaps;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.auth.GooglePlayServicesAvailabilityException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.WeakHashMap;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //Type of maps
        mMap.setMapType(googleMap.MAP_TYPE_NORMAL);

        final WeakHashMap wMarker= new WeakHashMap<Marker,String>();


        //for Componentes of maps
        UiSettings uiSettings= mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setCompassEnabled(true);
        uiSettings.setMapToolbarEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);

        //Length and latitude
        LatLng latLng = new LatLng(-12.059477, -77.041509);

        //draggable is true
        //rotation is marker
        MarkerOptions markerOptions=new MarkerOptions()
                .position(latLng)
                .title("Alfonso Ugarte")
                .snippet("av. Brasil 1750")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_blue))
                .draggable(true)
                .rotation(270);
        Marker principal=mMap.addMarker(markerOptions);


        //marcadore 2
        Marker marker2=mMap.addMarker(new MarkerOptions().
                        position(new LatLng(-12.059480, -77.041513))
                        .title("nuevo marcador")
                        .snippet("subtitulo para las pruebas"));
        marker2.showInfoWindow();

        //marcadore 3
        Marker marker3 = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-12.059370, -77.041404))
                .flat(true));

        float zoomLevel=18;
        //zoom in the cam
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoomLevel));

        //events onclick
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(MapsActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
