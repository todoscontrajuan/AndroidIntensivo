package com.juanmanuelrodriguez.androidintensivo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate() method");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() method");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() method");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart() method");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() method");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() method");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() method");
    }

    public void showTime(View view) {
        Context context = getApplicationContext();

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss.mmm");
        String currentDateandTime = sdf.format(new Date());

        CharSequence text = currentDateandTime;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void textEditor(View view) {
        Intent intent = new Intent(this, TextEditorActivity.class);
        startActivity(intent);
    }

    public void gallery(View view) {
        Intent intent = new Intent(this, ImageActivity.class);
        startActivity(intent);
    }

    public void camera(View view) {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }

    public void thread(View view) {
        Intent intent = new Intent(this, ThreadsActivity.class);
        startActivity(intent);
    }

    public void download(View view) {
        Intent intent = new Intent(this, ImageDownloaderActivity.class);
        startActivity(intent);
    }

    public void listExercise(View view) {
        Intent intent = new Intent(this, ListViewActivity.class);
        startActivity(intent);
    }

    public void locationExercise(View view) {
        Intent intent = new Intent(this, LocationActivity.class);
        startActivity(intent);
    }

    public void restExercise(View view) {
        Intent intent = new Intent(this, RestActivity.class);
        startActivity(intent);
    }
}
