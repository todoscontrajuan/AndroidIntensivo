package com.juanmanuelrodriguez.androidintensivo;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by juanmanuelrodriguez on 6/30/16.
 */
public class ImageDownloaderService extends IntentService {

    private String url;

    public ImageDownloaderService() {
        super("ImageDownloaderService");
    }

    public void onDownload(Intent intent) {
        url = intent.getStringExtra("URL");

        String imagePath = null;
        try {
            //Se usa el tiempo para que el nombre del archivo sea único
            String timeStamp = new SimpleDateFormat("MMddyyyy_HHmmss").format(new Date());
            String imageFileName = "image_" + timeStamp;

            File storageDir = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS);
            File imageFile = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );

            InputStream imageInputStream = new URL(url).openStream();

            FileOutputStream imageOutputStream = new FileOutputStream(imageFile);

            int bytesRead = 0;
            byte[] buffer = new byte[8192]; //Esto se debería calcular (creo)
            while((bytesRead = imageInputStream.read(buffer)) != -1) {
                imageOutputStream.write(buffer, 0, bytesRead);
            }

            imagePath = imageFile.getAbsolutePath();
        } catch (Exception e) {
            Log.d("TRAINING","Error al descargar imagen");
            e.printStackTrace();
        }


        Intent i = new Intent("downloader-event");
        i.putExtra("returnedURL", imagePath);
        LocalBroadcastManager.getInstance(this).sendBroadcast(i);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        onDownload(intent);
    }
}
