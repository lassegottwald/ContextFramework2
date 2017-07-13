package com.lassegottwald.contextframework.helper;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by lgottwald on 13.07.2017.
 */

public class LogBroadcast {
    private Context context;

    public LogBroadcast(Context context) {
        this.context = context;
    }

    public void sendLog(String source, String msg) {
        Log.v("LogBroadcast", "Log send: " + source + " : " + msg);
        Intent intent = new Intent();
        intent.setAction("com.lassegottwald.contextframework.LOG_BROADCAST");
        intent.putExtra("source", source);
        intent.putExtra("msg", msg);
        this.context.sendBroadcast(intent);

    }
}
