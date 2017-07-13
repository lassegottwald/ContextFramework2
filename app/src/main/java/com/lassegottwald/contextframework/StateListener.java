package com.lassegottwald.contextframework;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by lgottwald on 10.07.2017.
 */

public class StateListener extends BroadcastReceiver {

    public StateListener() {
        Log.v("StateListener", "Constructor");
    }

    public void onReceive(Context c, Intent intent) {
        Log.v("StateListener", "Yay the screen was unlocked!");
        //Log.e("StateListener", "Yay the screen was unlocked!");
        //Toast.makeText(c, "da is was los", Toast.LENGTH_LONG);
        //Object o = intent.getExtras().get("data");
        //System.out.println(intent.toString());
        //Log.v("StateListener", o.toString());
        //Cursor sensorData = getContentResolver().query("content://com.aware.provider.locations/locations", tableColumns, whereCondition, whereArguments, orderBy);
    }


}
