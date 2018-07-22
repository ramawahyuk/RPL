package team.dev.helpy.rame.Maps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import team.dev.helpy.rame.GetNearbyPlacesData;
import team.dev.helpy.rame.MapsActivity;
import team.dev.helpy.rame.R;

import static android.view.Gravity.CENTER;


public class Maps extends Fragment {
    MapView mMapView;
    private GoogleMap googleMap;
    public LocationManager locationManager;
    String snippet;
    String title;
    double latitude, longitude;
    private Map<String, Object> dataModel = new HashMap<>();
    private EditText mSearchtext;
    private static final String TAG="Maps";
    int PROXIMITY_RADIUS = 500;
    private Context mContext;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.g_map);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @SuppressLint("MissingPermission")
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                float zoom = 16;

                Location location;

                //latlong posisi fix
                LatLng floatingMarket = new LatLng(-6.819143, 107.618487);
                LatLng Dagoterrace = new LatLng(-6.890218, 107.612694);
                LatLng dpakar = new LatLng(-6.846587, 107.649610);
                LatLng yagami = new LatLng(-6.884591, 107.613557);
                LatLng TSC = new LatLng(-6.865216, 107.627020);
                LatLng cikapundung = new LatLng(-6.871647, 107.620117);
                LatLng dusunBambu = new LatLng(-6.789476, 107.578828);
                LatLng upsideDown = new LatLng(-6.896304, 107.617006);
                LatLng grafikaCikole = new LatLng(-6.785142, 107.651489);
                LatLng tahura = new LatLng(-6.856592, 107.632667);
                LatLng domino = new LatLng(-6.880370, 107.615968);
                LatLng gua = new LatLng(-6.854459, 107.637791);
                LatLng selasar = new LatLng(-6.858506, 107.636548);
                LatLng cartil = new LatLng(-6.855217, 107.666540);
                LatLng curugdago = new LatLng(-6.865522, 107.618188);
                LatLng congo= new LatLng(-6.867919, 107.627788);
                LatLng baksil = new LatLng(-6.884889, 107.610111);
                LatLng cabe = new LatLng(-6.891581, 107.615055);
                LatLng geprek = new LatLng(-6.891258, 107.616401);
                LatLng eduplex = new LatLng(-6.896556, 107.613043);
                LatLng eatbossdago = new LatLng(-6.897377, 107.613079);
                LatLng martabaksf = new LatLng(-6.927367, 107.619191);
                LatLng kartika = new LatLng(-6.897467, 107.612313);
                LatLng b_bujangan = new LatLng(-6.898270, 107.613275);
                LatLng bonbin = new LatLng(-6.890287, 107.606840);
                LatLng WS = new LatLng(-6.890193, 107.616211);
                LatLng nasgor = new LatLng(-6.891874, 107.617416);
                LatLng moci = new LatLng(-6.888002, 107.618443);
                LatLng golden = new LatLng(-6.887629, 107.613173);
                LatLng kedai = new LatLng(-6.892185, 107.612787);
                LatLng bbq = new LatLng(-6.871647, 107.620117);
                LatLng bene = new LatLng(-6.888027, 107.612978);
                LatLng dcubes = new LatLng(-6.885465, 107.614431);
                LatLng aris = new LatLng(-6.894603, 107.617463);
                LatLng star = new LatLng(-6.896067, 107.616802);
                LatLng kopi_km = new LatLng(-6.897768, 107.609913);
                LatLng angkringan = new LatLng(-6.895006, 107.609241);
                LatLng noah = new LatLng(-6.887212, 107.612445);
                LatLng mie = new LatLng(-6.882464, 107.615165);




                final Marker bonbinMarker = mMap.addMarker((new MarkerOptions().position(bonbin).title("Kebon Binatang bandung")));
                final Marker WSMarker = mMap.addMarker((new MarkerOptions().position(WS).title("Waroeng Steak House and Shake dipatiukur")));
                final Marker nasgorMarker = mMap.addMarker((new MarkerOptions().position(nasgor).title("Nasi Goreng Mafia DipatiUkur")));
                final Marker mociMarker = mMap.addMarker((new MarkerOptions().position(moci).title("Mochilok Dipatiukur")));
                final Marker goldenMarker = mMap.addMarker((new MarkerOptions().position(golden).title("Golden Monkeys Dago cafe")));
                final Marker kedaiMarker = mMap.addMarker((new MarkerOptions().position(kedai).title("Kedai Timbel Dago")));
                final Marker bbqMarker = mMap.addMarker((new MarkerOptions().position(bbq).title("Barbecue Restaurant : Babakaran")));
                final Marker beneMarker = mMap.addMarker((new MarkerOptions().position(bene).title("Caffe Bene Dago")));
                final Marker dcubesMarker = mMap.addMarker((new MarkerOptions().position(dcubes).title("D'cubes Hangout Point")));
                final Marker arisMarker = mMap.addMarker((new MarkerOptions().position(aris).title("Bebeke Om Aris")));
                final Marker starMarker = mMap.addMarker((new MarkerOptions().position(star).title("Starbuck CAfe Dipatiukur")));
                final Marker kopi_kmMarker = mMap.addMarker((new MarkerOptions().position(kopi_km).title("Kopi Kamu Taman Sari")));
                final Marker angkringanMarker = mMap.addMarker((new MarkerOptions().position(angkringan).title("Angkringan Mas Jo")));
                final Marker noahMarker = mMap.addMarker((new MarkerOptions().position(noah).title("Noah's Barn Dago")));
                final Marker mieMarker = mMap.addMarker((new MarkerOptions().position(mie).title("Kedai Mie Dago")));
                final Marker TSCMarker = mMap.addMarker((new MarkerOptions().position(TSC).title("The Stone Cafe bandung")));
                final Marker yagamiMarker = mMap.addMarker((new MarkerOptions().position(yagami).title("Bukit Moko")));
                final Marker cikapundungMarker = mMap.addMarker((new MarkerOptions().position(cikapundung).title("Kambing Soon Barbecue Restaurant")));
                final Marker dusunMarker = mMap.addMarker((new MarkerOptions().position(dusunBambu).title("Dusun Bambu")));
                final Marker dominoMarker = mMap.addMarker((new MarkerOptions().position(domino).title("Domino Pizza Dago Atas")));
                final Marker dpakarMarker = mMap.addMarker((new MarkerOptions().position(dpakar).title(" Cafe D'pakar")));
                final Marker dagoterraceMarker = mMap.addMarker((new MarkerOptions().position(Dagoterrace).title("Dago Terrace cafe and resto")));
                final Marker floatingMarker = mMap.addMarker((new MarkerOptions().position(floatingMarket).title("Floating Market Lembang")));
                final Marker usdMarker = mMap.addMarker((new MarkerOptions().position(upsideDown).title("Upside Down")));
                final Marker cikoleMarker = mMap.addMarker((new MarkerOptions().position(grafikaCikole).title("Grafika Cikole Lembang")));
                final Marker tahuraMarker = mMap.addMarker((new MarkerOptions().position(tahura).title("Taman Hutan Raya Ir.H.Djuanda")));
                final Marker kartikaMarker = mMap.addMarker((new MarkerOptions().position(kartika).title("Kartika Sari Dago")));
                final Marker martabaksfMarker = mMap.addMarker((new MarkerOptions().position(martabaksf).title("Martabak San Fransisco")));
                final Marker eatbossdagoMarker = mMap.addMarker((new MarkerOptions().position(eatbossdago).title("Eatboss Dago")));
                final Marker eduplexMarker = mMap.addMarker((new MarkerOptions().position(eduplex).title("Eduplex Workspace and Coworking")));
                final Marker geprekMarker = mMap.addMarker((new MarkerOptions().position(geprek).title("Ayam geprek Bebas")));
                final Marker cabeMarker = mMap.addMarker((new MarkerOptions().position(cabe).title("Cabe rawit Cafe")));
                final Marker baksilMarker = mMap.addMarker((new MarkerOptions().position(baksil).title("Babakan Siliwangi")));
                final Marker b_bujanganMarker = mMap.addMarker((new MarkerOptions().position(b_bujangan).title("Bakso Boedjangan Dipatiukur")));
                final Marker congoMarker = mMap.addMarker((new MarkerOptions().position(congo).title("Congo Gallery and Cafe")));
                final Marker curugdagoMarker = mMap.addMarker((new MarkerOptions().position(curugdago).title("Curug Dago")));
                final Marker cartilMarker = mMap.addMarker((new MarkerOptions().position(cartil).title("Caringin Tilu")));
                final Marker selasarMarker = mMap.addMarker((new MarkerOptions().position(selasar).title("Selasar Sunaryo Art Space")));
                final Marker guaMarker = mMap.addMarker((new MarkerOptions().position(gua).title("Gua Belanda")));




                LatLng bdg = new LatLng(-6.90389, 107.61861);
                    mMap.setMyLocationEnabled(true);
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bdg,zoom));
                googleMap.addMarker(new MarkerOptions().position(bdg).title("Bandung").snippet("Total Populasi :8,201,928"));
                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {

                    return false;
                }
            });
                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(bdg).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            }

        });


        return rootView;
    }
    public void onClick(View v){

        Object dataTransfer[] = new Object[2];
        GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
        switch(v.getId()) {
            case R.id.B_search:
                EditText tf_location = (EditText)v.findViewById(R.id.TF_location);
                String location = tf_location.getText().toString();
                List<Address> addressList;


                if (!location.equals("")) {
                    Geocoder geocoder = new Geocoder(mContext,Locale.getDefault());

                    try {
                        addressList = geocoder.getFromLocationName(location, 100);

                        if (addressList != null) {
                            for (int i = 0; i < addressList.size(); i++) {
                                LatLng latLng = new LatLng(addressList.get(i).getLatitude(), addressList.get(i).getLongitude());
                                MarkerOptions markerOptions = new MarkerOptions();
                                markerOptions.position(latLng);
                                markerOptions.title(location);
                                googleMap.addMarker(markerOptions);
                                googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                                googleMap.animateCamera(CameraUpdateFactory.zoomTo(16));
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.B_restaurants:
                googleMap.clear();
                dataTransfer = new Object[2];
                String restaurant = "restoran";
                String url = getUrl(latitude, longitude, restaurant);
                getNearbyPlacesData = new GetNearbyPlacesData();
                dataTransfer[0] = googleMap;
                dataTransfer[1] = url;

                getNearbyPlacesData.execute(dataTransfer);

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
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }


}
