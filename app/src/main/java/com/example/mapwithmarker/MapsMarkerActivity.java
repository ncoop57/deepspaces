package com.example.mapwithmarker;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.orhanobut.dialogplus.DialogPlus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * An activity that displays a Google map with a marker (pin) to indicate a particular location.
 */
public class MapsMarkerActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    Button showDialogButton;
    private static final String TAG = MapsMarkerActivity.class.getName();
    private static final LatLng POS1 = new LatLng(30.547437, -87.217710 );
    private static final LatLng POS2 = new LatLng(30.547377, -87.217799);
  //  private static final LatLng POS3 = new LatLng(30.547220, -87.217399);

    private Marker mPos1;
    private Marker mPos2;
    private Marker marker;
 //   private Marker mPos3;

    private GoogleMap mMap;

    private RequestQueue mRequestQueue;
    private StringRequest stringRequest;



    private String url = "http://54.145.198.32/get_spaces";
    private String resp;
    private void sendRequest() {
        mRequestQueue = Volley.newRequestQueue(this);

        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("result", "Response:" + response.toString());
                resp = response.toString();
//                Log.i()
                try {
                    JSONArray jsonList = new JSONArray(resp);

                    for(int i = 0; i<jsonList.length(); i++){
                        JSONObject obj = (JSONObject) jsonList.get(i);
                        double lat = obj.getDouble("gps_lat");
                        double lng = obj.getDouble("gps_lng");
                        LatLng pos = new LatLng(lat, lng );
                        marker = mMap.addMarker(new MarkerOptions().position(pos).title("dsfad")
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                        marker.setTag(0);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("ErrorResponse", volleyError.toString());
            }
        });
        mRequestQueue.add(stringRequest);
    }

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

        // get the dialog box in the bottom, uses teh dialog button
        showDialogButton = (Button) findViewById(R.id.showDialogButton);

        final DialogPlus dialog = DialogPlus.newDialog(this)
                .setGravity(Gravity.BOTTOM)
                .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                .create();

        showDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequest();

//                dialog.show();
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
        mMap = googleMap;

        mPos1 = mMap.addMarker(new MarkerOptions().position(POS1).title("dsfad")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mPos1.setTag(0);

        mPos2 = mMap.addMarker(new MarkerOptions().position(POS2).title("hgfh")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mPos2.setTag(0);

        LatLng s1 = new LatLng(30.547105, -87.217556);
        googleMap.addMarker(new MarkerOptions().position(s1).title("adfs")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));


      /*  LatLng sydney = new LatLng(30.547105, -87.217556);
        LatLng s1 = new LatLng(30.547377, -87.217799);
        LatLng s2 = new LatLng(30.547220, -87.217399);*/


        /*googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Type: Disability"));

        googleMap.addMarker(new MarkerOptions().position(s1).title("Type: Commute"));
        googleMap.addMarker(new MarkerOptions().position(s2).title("Type: Stuff"));*/




        //zoom method 1
        float zoomLevel = 19.5f; //This goes up to 21
    //    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom((LatLng)mPos1, zoomLevel));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s1,zoomLevel));
     //   googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(s2,zoomLevel));


        //update the camera
    //    googleMap.moveCamera(CameraUpdateFactory.newLatLng((Marker)mPos1));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(s1));
     //   googleMap.moveCamera(CameraUpdateFactory.newLatLng(s2));



    }
    protected void getSpacesPeriodically() {
        final Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                //Location l = (Location) msg.obj;
                //Toast.makeText(getApplicationContext(), "Latitude: " + String.valueOf(l.getLatitude()) + " Longitude: " + String.valueOf(l.getLongitude()),
                 //       Toast.LENGTH_LONG).show();
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10000);
                        Log.i(TAG, "Running");
                    } catch (Exception e) {
                    }
                }
            }
        }).start();
    }


}

