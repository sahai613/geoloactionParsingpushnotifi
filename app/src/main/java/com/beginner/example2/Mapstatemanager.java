package com.beginner.example2;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by sahai613 on 20-11-2017.
 */

public class Mapstatemanager {
    private static final String Longitude="longitude";
    private static final String Latitude="latitude";
    private static final String Zoom="zoom";
    private static final String Bearing="bearing";
    private static final String Tilt="tilt";
    private static final String MapType="maptype";
    private static final String Prefs_Name ="mapCameraState";
    private SharedPreferences mapStateprefs;

    public Mapstatemanager(Context context){
        mapStateprefs=context.getSharedPreferences(Prefs_Name,Context.MODE_PRIVATE);
    }
    public void Savedmapstate(GoogleMap map){
        SharedPreferences.Editor editor=mapStateprefs.edit();
        CameraPosition position=map.getCameraPosition();
        editor.putFloat(Latitude, (float) position.target.latitude);
        editor.putFloat(Longitude, (float) position.target.longitude);
        editor.putFloat(Zoom,position.zoom);
        editor.putFloat(Bearing,position.bearing);
        editor.putFloat(Tilt,position.tilt);
        editor.putInt(MapType,map.getMapType());
        editor.commit();
    }
    public CameraPosition getSavedCameraPosition(){
        double latitude=mapStateprefs.getFloat(Latitude,0);
        double longitude=mapStateprefs.getFloat(Longitude,0);
        LatLng target= new LatLng(latitude,longitude);

        float zoom=mapStateprefs.getFloat(Zoom,0);
        float bearing=mapStateprefs.getFloat(Bearing,0);
        float tilt=mapStateprefs.getFloat(Tilt,0);
        CameraPosition position=new CameraPosition(target,zoom,tilt,bearing);
        return position;

    }
}
