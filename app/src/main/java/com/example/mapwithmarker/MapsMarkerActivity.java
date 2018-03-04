package com.example.mapwithmarker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.orhanobut.dialogplus.DialogPlus;

/**
 * An activity that displays a Google map with a marker (pin) to indicate a particular location.
 */
public class MapsMarkerActivity extends AppCompatActivity
        implements OnMapReadyCallback {

        Button showDialogButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_maps);
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        showDialogButton = (Button) findViewById(R.id.showDialogButton);

        final DialogPlus dialog = DialogPlus.newDialog(this)
                .setGravity(Gravity.BOTTOM)
                .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                .create();

        showDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
    }

    /**
     * Manipulates the map when it's available.
     * The API invokes this callback when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user receives a prompt to install
     * Play services inside the SupportMapFragment. The API invokes this method after the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // and move the map's camera to the same location.
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);



        LatLng sydney = new LatLng(30.547105, -87.217556);
        LatLng s1 = new LatLng(30.547377, -87.217799);
        LatLng s2 = new LatLng(30.547220, -87.217399);


        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Type: Disability"));

        googleMap.addMarker(new MarkerOptions().position(s1).title("Type: Commute"));
        googleMap.addMarker(new MarkerOptions().position(s2).title("Type: Stuff"));




        //zoom method 1
        float zoomLevel = 19.5f; //This goes up to 21
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s1,zoomLevel));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s2,zoomLevel));


        //update the camera
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(s1));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(s2));



    }


}
