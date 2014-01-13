package com.example.mappadding;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;

/**
 * Use map padding to allow a sidebar to hide part of a map without
 * hiding the Google logo.
 */
public class MainActivity extends FragmentActivity {
    /**
     * Note that this may be null if the Google Play services APK is not available.
     */
    private GoogleMap mMap;

    /** 
     * Set latitude/longitude of 3 default locations.
     */
    private static final LatLng SOH = new LatLng(-33.85704, 151.21522);
    private static final LatLngBounds AUS = new LatLngBounds(
            new LatLng(-44, 113), new LatLng(-10, 154));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_FULLSCREEN);
        setUpMapIfNeeded();
    }
    
    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.
     * <p>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                   .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
                // Set map padding to none, and move the camera to Sydney Opera House.
                mMap.setPadding(0, 0, 0, 0);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SOH, 6));
                // Add a marker to the Opera House
                mMap.addMarker(new MarkerOptions().position(SOH).title("Sydney Opera House"));
            }
        }
    }

    public void goToAUS(View view) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(AUS, 0));
    }

    public void setNoPadding(View view) {
        mMap.setPadding(0, 0, 0, 0);
    }

    public void setPadding(View view) {
        mMap.setPadding(0, 0, 300, 0);
    }
}

