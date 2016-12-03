package com.hermes.gps;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.orm.query.Condition;
import com.orm.query.Select;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MapView extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    String newString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googlemap);
        mapFragment.getMapAsync(MapView.this);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("STRING_I_NEED");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("STRING_I_NEED");
        }
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (newString!=null)
        {
            // Add a marker in Sydney, Australia, and move the camera.
            JSONArray jsonarray = null;
            try {
                jsonarray = new JSONArray(newString);
                int end=jsonarray.length();
                for(int i=0; i<jsonarray.length(); i++)
                {
                    JSONObject issue              = jsonarray.getJSONObject(i);
                    int idapp                     = issue.getInt("id");
                    String B                      = issue.getString("B");
                    String C                      = issue.getString("C");
                    int E                         = issue.getInt("E");
                    String date                   = issue.optString("date");
                    String time                   = issue.optString("time");
                    System.out.println("B: "+B);
                    System.out.println("C: "+C);
                    System.out.println("E: "+E);
                    System.out.println("date: "+date);
                    System.out.println("time: "+time);
                    LatLng sydney = new LatLng(Double.parseDouble(B), Double.parseDouble(C));
                    if(E<=60 && E>=0)
                    {
                        mMap    .addMarker(new MarkerOptions()
                                .position(sydney)
                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_green))
                                .title("(Number: "+i+") Speed: "+E +"KM/H  Time:"+time));
                    }
                    else if(E<=90 && E>=61)
                    {
                        mMap    .addMarker(new MarkerOptions()
                                .position(sydney)
                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_yellow))
                                .title("(Number: "+i+") Speed: "+E +"KM/H  Time:"+time));
                    }
                    else if(E<=110 && E>=91)
                    {
                        mMap    .addMarker(new MarkerOptions()
                                .position(sydney)
                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_yellow))
                                .title("(Number: "+i+") Speed: "+E +"KM/H  Time:"+time));
                    }
                    else if(E>110)
                    {
                        mMap    .addMarker(new MarkerOptions()
                                .position(sydney)
                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_yellow))
                                .title("(Number: "+i+") Speed: "+E +"KM/H  Time:"+time));
                    }

                    if(i==0)
                    {
                        mMap    .addMarker(new MarkerOptions()
                                .position(sydney)
                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_startline))
                                .title("(Number: "+i+") Speed: "+E +"KM/H  Time:"+time));
                    }
                    if(i==end)
                    {
                        mMap    .addMarker(new MarkerOptions()
                                .position(sydney)
                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_endline))
                                .title("(Number: "+i+") Speed: "+E +"KM/H  Time:"+time));
                    }
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                    mMap.animateCamera( CameraUpdateFactory.zoomTo( 12.0f ) );
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
