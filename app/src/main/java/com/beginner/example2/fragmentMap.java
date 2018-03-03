package com.beginner.example2;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

/**
 * Created by sahai613 on 30-10-2017.
 */
public class fragmentMap extends Fragment implements OnMapReadyCallback {

    GoogleMap mMap;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        final EditText locationSearch = (EditText) v.findViewById(R.id.geotext);


        Button go = (Button) v.findViewById(R.id.geo);
        setupMapIfNeeded();

        go.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String location = locationSearch.getText().toString();
                List<Address> addressList = null;
                mMap.clear();

                {
                    Geocoder geocoder = new Geocoder(getContext());
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);


                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

                    Marker mymarker = mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(12).build();
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


                }
            }


        });






        return v;
    }
    private void setupMapIfNeeded() {
        if (mMap == null) {

            SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // For showing a move to my location button
        if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }


        googleMap.setMyLocationEnabled(true);
        // Enable MyLocation Layer of Google Map
        UiSettings mapSettings;
        mapSettings = googleMap.getUiSettings();
        mapSettings.setZoomGesturesEnabled(true);
        mapSettings.setScrollGesturesEnabled(true);
        mapSettings.setTiltGesturesEnabled(true);
        mapSettings.setRotateGesturesEnabled(true);


        // For dropping a marker at a point on the Map
        LatLng sydney = new LatLng(-34, 151);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));
        Mapstatemanager mgr = new Mapstatemanager(getContext());
        CameraPosition position = mgr.getSavedCameraPosition();
        if (position != null) {
            CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);
            mMap.moveCamera(update);

             }

////                 For zooming automatically to the location of the marker
//               CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(location.getLatitude(),location.getLongitude())).zoom(12).build();
//                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }
    @Override
    public void onPause() {
        super.onPause();
        Mapstatemanager mgr = new Mapstatemanager(getContext());
        mgr.Savedmapstate(mMap);
    }

    @Override
    public void onResume() {
        super.onResume();
        setupMapIfNeeded();
    }


}











