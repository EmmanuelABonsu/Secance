package com.example.secance;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Dashboard extends FragmentActivity implements OnMapReadyCallback {
    DrawerLayout drawerLayout;

    GoogleMap map;

    ArrayList<LatLng> coordinates = new ArrayList<LatLng>();
    LatLng Liverpool = new LatLng(54.62943523205739, -3.6593036536767296);
    LatLng Manchester = new LatLng(53.486928268615344, -2.2443897279584166);
    LatLng London = new LatLng(51.519109433320324, -0.1325249451116302);

    ArrayList<String> places = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        drawerLayout = findViewById(R.id.drawer_layout);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        coordinates.add(Liverpool);
        coordinates.add(Manchester);
        coordinates.add(London);

        places.add("Liverpool");
        places.add("Manchester");
        places.add("London");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        for (int i= 0; i < coordinates.size(); i++) {
            for (int j= 0; j < places.size(); j++) {
                map.addMarker(new MarkerOptions().position(coordinates.get(i)).title(String.valueOf(places.get(j))));
            }
            map.moveCamera(CameraUpdateFactory.newLatLng(coordinates.get(i)));
        }

        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String place_marker = marker.getTitle();

                Intent intent = new Intent(Dashboard.this, TaskRecyclerActivity.class);
                intent.putExtra("title", place_marker);
                startActivity(intent);

                return false;
            }
        });

    }
}