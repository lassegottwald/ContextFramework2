package com.lassegottwald.contextframework;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.lassegottwald.contextframework.contextservice.ContextService;
import com.lassegottwald.contextframework.helper.LogBroadcast;


public class MainActivity extends AppCompatActivity implements LoggerFragment.OnFragmentInteractionListener {

    private Context context;
    private LogBroadcast logger;
    private static StateListener sl = new StateListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("MAINACTIVITY", "AppRunning");

        this.context = getApplicationContext();
        this.logger = new LogBroadcast(this.context);
        this.logger.sendLog("MainActivity","ACTIVITY LAUNCH");
        Intent contextIntent = new Intent(this.context, ContextService.class);
        this.context.startService(contextIntent);


        //Initialise AWARE
        //Intent aware = new Intent(this.context, Aware.class);
        //startService(aware);
        //Aware.setSetting(this.context, Aware_Preferences.DEBUG_FLAG, true );
        //Log.v("MAINACTIVITY", Aware_Preferences.DEBUG_FLAG);

        //Aware.reset(this.context);
       // Aware.
        //Activate Accelerometer
        //Aware.setSetting(this, Aware_Preferences.STATUS_ACCELEROMETER, true);
        //Set sampling frequency
        //Aware.setSetting(this, Aware_Preferences.FREQUENCY_ACCELEROMETER, 10000000);
        //Apply settings
        //Aware.startSensor(this, Aware_Preferences.STATUS_ACCELEROMETER);

        //Aware.setSetting(this, Aware_Preferences.STATUS_ACCELEROMETER, false);


        //Aware.setSetting(this, Aware_Preferences.STATUS_LIGHT, true);
        //Aware.startSensor(this, Aware_Preferences.STATUS_LIGHT);
        //Aware.setSetting(this, Aware_Preferences.STATUS_LIGHT, false);

        //Aware.setSetting(this, Aware_Preferences.STATUS_LOCATION_GPS, false);
 //       Aware.setSetting(this, Aware_Preferences.FREQUENCY_LOCATION_GPS,0);
  //      Aware.startSensor(this, Aware_Preferences.STATUS_LOCATION_GPS);
/**
        IntentFilter broadcastFilter = new IntentFilter();
        broadcastFilter.addAction(Locations.ACTION_AWARE_LOCATIONS);
        registerReceiver(sl, broadcastFilter);
*/

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
