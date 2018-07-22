package team.dev.helpy.rame;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.common.api.GoogleApiClient;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    GoogleApiClient mGoogleApiClient;
        private Button logout;
        private GoogleMap mMap;
        private LocationRequest locationRequest;
        public static final int REQUEST_LOCATION_CODE = 99;
        double latitude, longitude;
        int PROXIMITY_RADIUS = 500;
        private static final String TAG="MapsActivity";
        private GoogleApiClient client;
        private Marker currentLocationmMarker;
        private Location lastlocation;
        FusedLocationProviderClient mFusedLocationClient;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_maps);
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    checkLocationPermission();

                }
            mapFragment.getMapAsync(this);
            logout=(Button)findViewById(R.id.logout);

        }




    public boolean checkLocationPermission()
    {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)  != PackageManager.PERMISSION_GRANTED )
        {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION))
            {
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION },REQUEST_LOCATION_CODE);
            }
            else
            {
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION },REQUEST_LOCATION_CODE);
            }
            return false;

        }
        else
            return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode)
        {
            case REQUEST_LOCATION_CODE:
                if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) !=  PackageManager.PERMISSION_GRANTED)
                    {
                        if(client == null)
                        {
                            bulidGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                }
                else
                {
                    Toast.makeText(this,"Permission Denied" , Toast.LENGTH_LONG).show();
                }
        }
    }

    protected synchronized void bulidGoogleApiClient() {
        client = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        client.connect();

    }

    public void onClick(View v){
        Object dataTransfer[] = new Object[2];
        GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
        switch(v.getId()) {
            case R.id.B_search:
                EditText tf_location = findViewById(R.id.TF_location);
                String location = tf_location.getText().toString();
                List<Address> addressList;


                if (!location.equals("")) {
                    Geocoder geocoder = new Geocoder(this);

                    try {
                        addressList = geocoder.getFromLocationName(location, 100);

                        if (addressList != null) {
                            for (int i = 0; i < addressList.size(); i++) {
                                LatLng latLng = new LatLng(addressList.get(i).getLatitude(), addressList.get(i).getLongitude());
                                MarkerOptions markerOptions = new MarkerOptions();
                                markerOptions.position(latLng);
                                markerOptions.title(location);
                                mMap.addMarker(markerOptions);
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                                mMap.animateCamera(CameraUpdateFactory.zoomTo(16));
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.B_restaurants:
                mMap.clear();
                dataTransfer = new Object[2];
                String restaurant = "restaurant";
                String url = getUrl(latitude, longitude, restaurant);
                getNearbyPlacesData = new GetNearbyPlacesData();
                dataTransfer[0] = mMap;
                dataTransfer[1] = url;

                getNearbyPlacesData.execute(dataTransfer);
                Toast.makeText(MapsActivity.this, "Showing Nearby Restaurants", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private String getUrl(double latitude , double longitude , String nearbyPlace)
    {
        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlaceUrl.append("location="+latitude+","+longitude);
        googlePlaceUrl.append("&radius="+PROXIMITY_RADIUS);
        googlePlaceUrl.append("&type="+nearbyPlace);
        googlePlaceUrl.append("&sensor=true");
        googlePlaceUrl.append("&key="+"AIzaSyA819NonC8_YKUCJMeLDF_qFuEP7VfG884");
        Log.d("MapsActivity", "url = "+googlePlaceUrl.toString());
        return googlePlaceUrl.toString();
    }


    @Override
        public void onMapReady(GoogleMap googleMap) {

            mMap = googleMap;
            float zoom=14.0f;
            LatLng floatingMarket = new LatLng(-6.819143, 107.618487);
            LatLng Dagoterrace = new LatLng(-6.890218, 107.612694);
            LatLng dpakar = new LatLng(-6.846587, 107.649610);
            LatLng yagami = new LatLng(-6.884591, 107.613557);
            LatLng TSC = new LatLng(-6.865216, 107.627020);
            LatLng cikapundung = new LatLng(-6.871647, 107.620117);
            LatLng dusunBambu = new LatLng(-6.789476, 107.578828);
            LatLng upsideDown = new LatLng(-6.896304, 107.617006);
            LatLng grafikaCikole = new LatLng(-6.785142, 107.651489);
            LatLng TLM = new LatLng(-6.824706, 107.688650);
            LatLng domino = new LatLng(-6.880370, 107.615968);

            LatLng jakarta = new LatLng(-6,107);

            final Marker idnMarker = mMap.addMarker((new MarkerOptions().position(jakarta).title("Jakarta")));
            final Marker TSCMarker = mMap.addMarker((new MarkerOptions().position(TSC).title("The Stone Cafe bandung")));
            final Marker yagamiMarker = mMap.addMarker((new MarkerOptions().position(yagami).title("Bukit Moko")));
            final Marker cikapundungMarker = mMap.addMarker((new MarkerOptions().position(cikapundung).title("Kambing Soon Barbecue Restaurant")));
            final Marker dusunMarker = mMap.addMarker((new MarkerOptions().position(dusunBambu).title("Dusun Bambu")));
            final Marker dominoMarker = mMap.addMarker((new MarkerOptions().position(domino).title("Domino Pizza Dago Atas")));


            mMap.addMarker(new MarkerOptions().position(jakarta).title("Jakarta"));
          //  mMap.moveCamera(CameraUpdateFactory.newLatLng(jakarta));
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
                        Toast.makeText(MapsActivity.this, "you click jakarta", Toast.LENGTH_SHORT).show();
                    }
                    if (marker.equals((TSCMarker))) {
                        Toast.makeText(MapsActivity.this, "The Stone Cafe bandung ", Toast.LENGTH_SHORT).show();
                    }
                    if (marker.equals((yagamiMarker))) {
                        Toast.makeText(MapsActivity.this, "Yagami Ramen House Dago", Toast.LENGTH_SHORT).show();
                    }
                    if (marker.equals((cikapundungMarker))) {
                        Toast.makeText(MapsActivity.this, "Kambing Soon Barbecue Restaurant", Toast.LENGTH_SHORT).show();
                    }
                    if (marker.equals((dusunMarker))) {
                        Toast.makeText(MapsActivity.this, "Tempat Wisata Dusun Bambu", Toast.LENGTH_SHORT).show();
                    }
                    if (marker.equals((dominoMarker))) {
                        Toast.makeText(MapsActivity.this, "Domino Pizza Dago atas", Toast.LENGTH_SHORT).show();
                    }
                    return false;
                }
            });
            if ((ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
                LatLng bdg = new LatLng(-6.90389,107.61861);
                mMap.setMyLocationEnabled(true);
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bdg,zoom));
            }
        }



        public void logout(View view){

            if (view==logout) {
                startActivity(new Intent(this ,Login.class));
                finish();
            }
        }
        private void createMarker(LatLng latLng) {
        }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
/*
        locationRequest = new LocationRequest();
        locationRequest.setInterval(100);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED)
        {
           // LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);
        }
*/
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}


