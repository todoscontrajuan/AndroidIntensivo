package com.juanmanuelrodriguez.androidintensivo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ThreadsActivity extends AppCompatActivity {

    private int timer = 0;
    private int timer2 = 0;
    private TextView threadTimer;
    private static String TAG = "ThreadDemo";
    private Thread t;
    private boolean running = true;
    private TimerAsyncTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threads);
        threadTimer = (TextView) findViewById(R.id.threadTimer);
    }

    public void startThread(View v) {

        t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(running) {
                    try {
                        Log.i(TAG, "Thread running");
                        Thread.sleep(1000);
                        timer++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }

    public void stopThread(View w) {
        t.interrupt();
        running = false;
        Log.i(TAG, "Thread stopped");
        threadTimer.setText(timer + " Segundos");
    }

    public void startTask(View v) {
        task = new TimerAsyncTask(this);
        task.execute(timer2);
    }

    public void stopTask(View v) {
        task.stopCounter();
    }

}
