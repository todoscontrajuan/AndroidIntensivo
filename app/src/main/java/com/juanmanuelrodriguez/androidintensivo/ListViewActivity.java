package com.juanmanuelrodriguez.androidintensivo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        listView = (ListView) findViewById(R.id.listView);

        String [] names = {"Lorem", "Ipsum", "Lorem", "Ipsum", "Lorem", "Ipsum", "Lorem", "Ipsum",
                "Lorem", "Ipsum", "Lorem", "Ipsum", "Lorem", "Ipsum"};

        List<String> namesList = Arrays.asList(names);

        ListViewAdapter listViewAdapter = new ListViewAdapter(this, namesList);
        listView.setAdapter(listViewAdapter);
    }

}
