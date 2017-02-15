package com.juanmanuelrodriguez.androidintensivo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RestActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);
        listView = (ListView) findViewById(R.id.listView2);

        RestAsyncTask personAsycTask = new RestAsyncTask();
        personAsycTask.execute();

        //ListViewAdapter listViewAdapter = new ListViewAdapter(this, namesList);
        //listView.setAdapter(listViewAdapter);
    }
}
