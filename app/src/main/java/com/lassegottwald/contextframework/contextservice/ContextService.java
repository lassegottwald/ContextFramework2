package com.lassegottwald.contextframework.contextservice;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.aware.Aware;
import com.aware.Aware_Preferences;
import com.lassegottwald.contextframework.contextservice.plugins.LocationPlugin;
import com.lassegottwald.contextframework.helper.LogBroadcast;

/**
 * Created by lgottwald on 10.07.2017.
 */

public class ContextService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    private LogBroadcast logger;

    public ContextService() {
        super("ContextService");


    }




    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        this.log("ContextService", "CONTEXT SERIVE LAUNCHED");

        LocationPlugin locationPlugin = new LocationPlugin(this);

        locationPlugin.startTracking();


    }



    private void log(String src, String msg) {
        Intent logIntent = new Intent();
        logIntent.setAction("com.lassegottwald.contextframework.LOG_BROADCAST");
        logIntent.putExtra("source", src);
        logIntent.putExtra("msg", msg);
        sendBroadcast(logIntent);
    }
}
