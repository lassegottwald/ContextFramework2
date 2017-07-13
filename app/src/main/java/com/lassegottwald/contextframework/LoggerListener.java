package com.lassegottwald.contextframework;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by lgottwald on 13.07.2017.
 */

public class LoggerListener extends BroadcastReceiver {
    private OnLogReceivedListener listener = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v("LoggerListener", "Log received");
        String logSrc = intent.getStringExtra("source");
        String logMsg = intent.getStringExtra("msg");
        Log.v("LoggerListener", logSrc + " : " + logMsg);
        if (listener != null) {
            listener.onLogReceived(logSrc, logMsg);
        }
    }

    public void setOnLogReceivedListener(LoggerFragment that) {
        this.listener = that;
    }


    public interface OnLogReceivedListener {
        void onLogReceived(String source, String msg);
    }
}
