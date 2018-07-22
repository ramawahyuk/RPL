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

public class MapsTebing extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_tebing);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        //init();
        mMap = googleMap;
        float zoom=14.0f;
        LatLng yagami = new LatLng(-6.884591, 107.613557);
        LatLng TSC = new LatLng(-6.865216, 107.627020);
        LatLng cikapundung = new LatLng(-6.871647, 107.620117);
        LatLng dusunBambu = new LatLng(-6.789476, 107.578828);
        LatLng upsideDown = new LatLng(-6.896304, 107.617006);
        LatLng grafikaCikole = new LatLng(-6.785142, 107.651489);
        LatLng floatingMarket = new LatLng(-6.819143, 107.618487);
        LatLng TLM = new LatLng(-6.824706, 107.688650);
        LatLng domino = new LatLng(-6.880370, 107.615968);
        LatLng Dagoterrace = new LatLng(-6.890218, 107.612694);
        LatLng dpakar = new LatLng(-6.846587, 107.649610);
        LatLng jakarta = new LatLng(-6,107);
        LatLng bdg = new LatLng(-6.834068, 107.663615);




        final Marker idnMarker = mMap.addMarker((new MarkerOptions().position(jakarta).title("Jakarta")));
        final Marker TSCMarker = mMap.addMarker((new MarkerOptions().position(TSC).title("The Stone Cafe bandung")));
        final Marker yagamiMarker = mMap.addMarker((new MarkerOptions().position(yagami).title("Bukit Moko")));
        final Marker cikapundungMarker = mMap.addMarker((new MarkerOptions().position(cikapundung).title("Kambing Soon Barbecue Restaurant")));



        mMap.addMarker(new MarkerOptions().position(bdg).title("Tebing Keraton").snippet("Jl.Ciburial, Cimenyan, Kabupaten Bandung Barat, Jawa Barat 40198").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
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
                    Toast.makeText(MapsTebing.this, "you click jakarta", Toast.LENGTH_SHORT).show();
                }
                if (marker.equals((TSCMarker))) {
                    Toast.makeText(MapsTebing.this, "The Stone Cafe bandung ", Toast.LENGTH_SHORT).show();
                }
                if (marker.equals((yagamiMarker))) {
                    Toast.makeText(MapsTebing.this, "Yagami Ramen House Dago", Toast.LENGTH_SHORT).show();
                }
                if (marker.equals((cikapundungMarker))) {
                    Toast.makeText(MapsTebing.this, "Kambing Soon Barbecue Restaurant", Toast.LENGTH_SHORT).show();
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
