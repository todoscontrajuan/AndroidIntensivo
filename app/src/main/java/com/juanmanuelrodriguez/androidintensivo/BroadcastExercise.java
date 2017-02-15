package com.juanmanuelrodriguez.androidintensivo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by juanmanuelrodriguez on 6/27/16.
 */
public class BroadcastExercise extends BroadcastReceiver {
    private SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Hora de encendido: " + sdf.format(new Date()) ,Toast.LENGTH_SHORT).show();
    }
}
