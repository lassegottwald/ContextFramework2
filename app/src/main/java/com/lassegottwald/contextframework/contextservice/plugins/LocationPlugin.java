package com.lassegottwald.contextframework.contextservice.plugins;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import java.util.List;

/**
 * Created by lgottwald on 13.07.2017.
 */

public class LocationPlugin implements LocationListener {
    private Context context;
    private LocationManager locManager;
    private Location lastLocation;

    public LocationPlugin(Context context) {
        this.context = context;
        this.log("LocationPlugin", "PLUGIN INITILAISED");

        this.locManager = (LocationManager) this.context.getSystemService(Context.LOCATION_SERVICE);

        List<String> locProviders = this.locManager.getProviders(true);
        this.log("LocationPlugin", "Providers: " + locProviders.toString());

    }


    public boolean startTracking() {
        this.log("LocationPlugin", "startTracking");


        /** if (ActivityCompat.checkSelfPermission(
         this.context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
         ActivityCompat.checkSelfPermission(this.context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
         */
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return TODO;
        }
        this.locManager.requestLocationUpdates(this.locManager.GPS_PROVIDER, 1000, 5, this);
            this.log("LocationPlugin", "lat: " + this.lastLocation.getLatitude() + " lng: " + this.lastLocation.getLongitude());
        /**
        return true;
        }
*/
        return false;
    }




    private void log(String src, String msg) {
        Intent logIntent = new Intent();
        logIntent.setAction("com.lassegottwald.contextframework.LOG_BROADCAST");
        logIntent.putExtra("source", src);
        logIntent.putExtra("msg", msg);
        this.context.sendBroadcast(logIntent);
    }

    @Override
    public void onLocationChanged(Location location) {
        this.lastLocation = location;
        this.log("LocationPlugin", "lat: " + this.lastLocation.getLatitude() + " lng: " + this.lastLocation.getLongitude());
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
