package com.example.onlinelibrary;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Location extends FragmentActivity implements OnMapReadyCallback{

   private GoogleMap mMap;
    ArrayList<LatLng> arrayList=new ArrayList<LatLng>();
    LatLng rmValcea =new LatLng(45.094565,24.365966);
    LatLng slatina =new LatLng(44.431869,24.367824);
    LatLng ASE=new LatLng(44.447916,26.099217);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        SupportMapFragment mapFragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        arrayList.add(rmValcea);
        arrayList.add(slatina);
        arrayList.add(ASE);
    }

    @Override
   public void onMapReady(GoogleMap googleMap) {
mMap=googleMap;
    for(int i=0;i<arrayList.size();i++){
        mMap.addMarker(new MarkerOptions().position(arrayList.get(i)).title("Marker"));
       mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));
    }
    }
}