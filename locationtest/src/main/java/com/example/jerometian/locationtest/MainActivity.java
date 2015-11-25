package com.example.jerometian.locationtest;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

/**
 * Created by jjtian on 2015/11/25.
 */
public class MainActivity extends AppCompatActivity {

    private AppCompatTextView positionTextView;
    private LocationManager locationManager;
    private String provider;
    private CoordinatorLayout cl;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if ( locationManager != null)
        {
          /*  if ( checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) !=PackageManager.PERMISSION_GRANTED)
            {
                return;
            }*/
            locationManager.removeUpdates(locationListener);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        positionTextView = (AppCompatTextView)findViewById(R.id.apptv1);
        cl = (CoordinatorLayout)findViewById(R.id.coorLayout1);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        List<String> providerList = locationManager.getProviders(true);
        if ( providerList.contains(LocationManager.GPS_PROVIDER))
        {
            provider = locationManager.GPS_PROVIDER;
        }
        else if (provider.contains(LocationManager.NETWORK_PROVIDER))
        {
            provider = LocationManager.NETWORK_PROVIDER;
        }
        else
        {
         /*   Snackbar.make(cl,"No location provider to use", Snackbar.LENGTH_LONG)
                    .setAction("Action",null).show();*/
            Toast.makeText(this, "No location provider to use",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        try {
           /* if ( checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) !=PackageManager.PERMISSION_GRANTED)
            {
                return;
            }*/
            Location location = locationManager.getLastKnownLocation(provider);


            if (location != null) {
                showLocation(location);
            }
          /*  while (location == null) {*/
                locationManager.requestLocationUpdates(provider, 5000, 1, locationListener);
            /*}*/
        }
        catch(Exception e)
        {
            Log.e("Location",e.getMessage());
        }
    }
    private void showLocation(Location location) {
        String currentPosition = "latitude is " + location.getLatitude() + "\n"
                + "longitude is " + location.getLongitude();
        positionTextView.setText(currentPosition);
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            showLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
}
