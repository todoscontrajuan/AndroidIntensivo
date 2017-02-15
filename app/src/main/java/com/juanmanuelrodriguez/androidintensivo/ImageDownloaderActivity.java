package com.juanmanuelrodriguez.androidintensivo;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class ImageDownloaderActivity extends AppCompatActivity {

    private EditText url;
    private String formattedUrl;
    private ImageView image;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_downloader);
        url = (EditText) findViewById(R.id.url);
        image = (ImageView) findViewById(R.id.imageView);

        // Register to receive messages.
        LocalBroadcastManager.getInstance(this).registerReceiver(downloaderReceiver,
                new IntentFilter("downloader-event"));
    }

    // The handler for received Intents.
    private BroadcastReceiver downloaderReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String imageUrl = intent.getStringExtra("returnedURL");
            if (imageUrl != null && !imageUrl.isEmpty()) {
                Bitmap imageBitmap = BitmapFactory.decodeFile(imageUrl);
                if (imageBitmap != null) {
                    image.setImageBitmap(imageBitmap);
                } else {
                    image.setImageResource(R.drawable.ic_map_black_24dp);
                }
            } else {
                image.setImageResource(R.drawable.ic_map_black_24dp);
            }
            hideLoadingIndicator();
        }
    };

    @Override
    protected void onDestroy() {
        // Unregister since the activity is about to be closed.
        LocalBroadcastManager.getInstance(this).unregisterReceiver(downloaderReceiver);
        super.onDestroy();
    }

    public void download(View v) {
        formattedUrl = url.getText().toString();
        Context context = getApplicationContext();
        Intent i = new Intent(context, ImageDownloaderService.class);
        if (formattedUrl.isEmpty() || formattedUrl.equals(null)) {
            Toast toast = Toast.makeText(context, "Inserte url por favor", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            i.putExtra("URL", formattedUrl);
            showLoadingIndicator();
            context.startService(i);
        }

    }

    private void showLoadingIndicator () {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(ImageDownloaderActivity.this);
            progressDialog.setMessage("Descargando imagen, espere...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(false);
        }

        progressDialog.show();
    }

    private void hideLoadingIndicator() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

}
