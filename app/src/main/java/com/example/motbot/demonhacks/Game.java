package com.example.motbot.demonhacks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.util.Log;
import android.view.Menu;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.common.ViewObject;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapCircle;
import com.here.android.mpa.mapping.MapFragment;
import com.here.android.mpa.mapping.MapGesture;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.MapObject;
import com.here.android.mpa.mapping.PositionIndicator;

public class Game extends Activity {

    private static final String LOG_TAG = Game.class.getSimpleName();

    // permissions request code
    private final static int REQUEST_CODE_ASK_PERMISSIONS = 1;

    private static final String[] REQUIRED_SDK_PERMISSIONS = new String[] {
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE };

    // map embedded in the map fragment
    private Map map = null;

    // map fragment embedded in this activity
    private MapFragment mapFragment = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        checkPermissions();

        Button shop = (Button)findViewById(R.id.shop);
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vendorr = new Intent(getApplicationContext(), vendor.class);
                startActivity(vendorr);
            }
        });

        }


    // Google has deprecated android.app.Fragment class. It is used in current SDK implementation.
    // Will be fixed in future SDK version.
    @SuppressWarnings("deprecation")
    private MapFragment getMapFragment() {
        return (MapFragment) getFragmentManager().findFragmentById(R.id.mapfragment);
    }

    private void initialize() {


        // Search for the map fragment to finish setup by calling init().
        mapFragment = getMapFragment();
        mapFragment.init(new OnEngineInitListener() {
            @Override
            public void onEngineInitializationCompleted(OnEngineInitListener.Error error) {
                if (error == OnEngineInitListener.Error.NONE) {

                    LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    Location location = lm.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
                    double longitude = location.getLongitude();
                    double latitude = location.getLatitude();

                    map = mapFragment.getMap();

                    GeoCoordinate storeLocation = new GeoCoordinate(41.878286, -87.625234, 0.0);
                    GeoCoordinate resourceLocation = new GeoCoordinate(41.878792, -87.625963, 0.0);
                    GeoCoordinate userLocation = new GeoCoordinate(latitude, longitude, 0.0);

                    map.setCenter(new GeoCoordinate(latitude, longitude, 0.0),
                            Map.Animation.NONE);

                    map.setZoomLevel(map.getMaxZoomLevel());
                    MapCircle userDot = new MapCircle(1, userLocation);
                    map.addMapObject(userDot);

                    com.here.android.mpa.common.Image storeImage = new com.here.android.mpa.common.Image();
                    try {
                        storeImage.setImageResource(R.drawable.coins);
                    } catch (IOException e) {
                        finish();
                    }

                    MapMarker storeDot = new MapMarker(storeLocation, storeImage);
                    map.addMapObject(storeDot);

                    com.here.android.mpa.common.Image resourceImage = new com.here.android.mpa.common.Image();
                    try {
                        resourceImage.setImageResource(R.drawable.diamond);
                    } catch (IOException e) {
                        finish();
                    }

                    MapMarker resourceDot = new MapMarker(resourceLocation, resourceImage);
                    map.addMapObject(resourceDot);

                    com.here.android.mpa.common.Image fortImage = new com.here.android.mpa.common.Image();
                    try {
                        fortImage.setImageResource(R.drawable.fort);
                    } catch (IOException e) {
                        finish();
                    }

                    MapMarker fortDot = new MapMarker(userLocation, fortImage);
                    map.addMapObject(fortDot);
                }


                    // Create a gesture listener and add it to the MapFragment


                    else Log.e(LOG_TAG, "Cannot initialize MapFragment (" + error + ")");}});}

    /**
     * Checks the dynamically controlled permissions and requests missing permissions from end user.
     */
    protected void checkPermissions() {
        final List<String> missingPermissions = new ArrayList<String>();
        // check all required dynamic permissions
        for (final String permission : REQUIRED_SDK_PERMISSIONS) {
            final int result = ContextCompat.checkSelfPermission(this, permission);
            if (result != PackageManager.PERMISSION_GRANTED) {
                missingPermissions.add(permission);
            }
        }
        if (!missingPermissions.isEmpty()) {
            // request all missing permissions
            final String[] permissions = missingPermissions
                    .toArray(new String[missingPermissions.size()]);
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_ASK_PERMISSIONS);
        } else {
            final int[] grantResults = new int[REQUIRED_SDK_PERMISSIONS.length];
            Arrays.fill(grantResults, PackageManager.PERMISSION_GRANTED);
            onRequestPermissionsResult(REQUEST_CODE_ASK_PERMISSIONS, REQUIRED_SDK_PERMISSIONS,
                    grantResults);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                for (int index = permissions.length - 1; index >= 0; --index) {
                    if (grantResults[index] != PackageManager.PERMISSION_GRANTED) {
                        // exit the app if one permission is not granted
                        Toast.makeText(this, "Required permission '" + permissions[index]
                                + "' not granted, exiting", Toast.LENGTH_LONG).show();
                        finish();
                        return;
                    }
                }
                // all permissions were granted
                initialize();
                break;
        }
    }
}
