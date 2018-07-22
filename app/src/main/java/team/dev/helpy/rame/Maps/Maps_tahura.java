package team.dev.helpy.rame.Maps;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import team.dev.helpy.rame.R;

public class Maps_tahura extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_tahura);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        float zoom=14.0f;
        LatLng bdg = new LatLng(-6.856592, 107.632667);
        final Marker idnMarker = mMap.addMarker((new MarkerOptions().position(bdg)));

        mMap.addMarker(new MarkerOptions().position(bdg).title("Taman hutan raya Ir.Djuanda").snippet("Kompleks Tahura Ir. H. Djuanda No.99, Dago Pakar, Ciburial, Cimenyan, Bandung, Jawa Barat 40198").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bdg));
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                createMarker(latLng);
                mMap.animateCamera(CameraUpdateFactory.zoomBy(16));
            }
        });
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (marker.equals((idnMarker))) {
                    Toast.makeText(Maps_tahura.this, "Taman Hutan Raya", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        if ((ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
            mMap.setMyLocationEnabled(true);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bdg,zoom));
        }
    }
    private void createMarker(LatLng latLng) {
    }
}
