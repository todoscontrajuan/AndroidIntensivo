package com.juanmanuelrodriguez.androidintensivo;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

/**
 * Created by juanmanuelrodriguez on 6/27/16.
 */
public class TimerAsyncTask extends AsyncTask<Integer, Integer, Integer> {
    private boolean runningTask = true;
    private TextView threadTimer;

    public TimerAsyncTask (Activity myContext) {
        threadTimer = (TextView) myContext.findViewById(R.id.taskTimer);
    }

    @Override
    protected Integer doInBackground(Integer... params) {
        while(runningTask){
            try {
                Thread.sleep(1000);
                params[0]++;
                publishProgress(params[0]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate(progress);
        threadTimer.setText(progress[0] + " segundos");
    }

    public void stopCounter() {
        runningTask = false;
    }

}
