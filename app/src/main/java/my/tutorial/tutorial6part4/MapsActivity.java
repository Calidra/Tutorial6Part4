package my.tutorial.tutorial6part4;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import my.tutorial.tutorial6part4.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    String streetAddress1;
    String streetAddress2;
    String streetAddress3;
    String areastr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        areastr = intent.getStringExtra("area");
        streetAddress1 = intent.getStringExtra("address1") + " " + areastr;
        streetAddress2 = intent.getStringExtra("address2") + " " + areastr;
        streetAddress3 = intent.getStringExtra("address3") + " " + areastr;
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng position1 = getLocationURLFromAddress(MapsActivity.this, streetAddress1);
        LatLng position2 = getLocationURLFromAddress(MapsActivity.this, streetAddress2);
        LatLng position3 = getLocationURLFromAddress(MapsActivity.this, streetAddress3);
        LatLng position4 = getLocationURLFromAddress(MapsActivity.this, areastr);

        if (position1 != null && position2 != null && position3 != null && position4 != null) {

            mMap.addMarker(new MarkerOptions().position(position1).title(streetAddress1));
            mMap.addMarker(new MarkerOptions().position(position2).title(streetAddress2));
            mMap.addMarker(new MarkerOptions().position(position3).title(streetAddress3));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(position3));
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
            mMap.animateCamera(CameraUpdateFactory.zoomTo(13), 2000, null);
        }
        else
        {
            LatLng sydney = new LatLng(-34, 151);
            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            Toast.makeText(getApplicationContext(),"Could not get address.", Toast.LENGTH_SHORT).show();
        }
    }

    public static LatLng getLocationURLFromAddress(Context context,
                                                   String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            android.location.Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            LatLng ret = new LatLng(location.getLatitude(), location.getLongitude());

            return ret;
            //
            // p1 = new LatLng(location.getLatitude(), location.getLongitude());

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return null;
    }



}