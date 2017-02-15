package com.juanmanuelrodriguez.androidintensivo;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by juanmanuelrodriguez on 7/12/16.
 */
public class RestAsyncTask extends AsyncTask<Void, Void, List<Person>> {

    @Override
    protected List<Person> doInBackground(Void... params) {
        List<Person> personList = new ArrayList<Person>();

        try {
            URL url = new URL("http://abelmartin.com.ar/mobilelab/fisintensivo.php");
            HttpURLConnection personConnection = (HttpURLConnection) url.openConnection();
            String personListStr = readStream(personConnection.getInputStream());
            personList = parseJSON(personListStr);

        } catch (MalformedURLException e) {
            Log.d("TRAINING", "Malformed URL");
        } catch (Exception e) {
            Log.d("TRAINING", "Error getting person list");
        }

        return personList;
    }


    private static String readStream (InputStream in) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        try {
            String nextLine = "";
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  sb.toString();
    }

    private List<Person> parseJSON(String jsonStr) {
        List<Person> personList = new ArrayList<Person>();
        try {
            JSONArray jsonArray = new JSONArray(jsonStr);
            for (int i=0; i < jsonArray.length(); i++) {

                JSONObject personJson = jsonArray.getJSONObject(i);

                Person person = new Person();
                person.setNombre(personJson.getString("name"));
                person.setCiudad(personJson.getString("address"));
                person.setImage(personJson.getString("profile_image"));
                personList.add(person);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return personList;
    }
}
