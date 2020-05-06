package com.sna.fototeka;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CatalogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        ListView listView = (findViewById(R.id.simpleListView));
        ArrayList<String> addedDocNames = new ArrayList<String>(MainActivity.documents.keySet());
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1, addedDocNames);
        listView.setAdapter(arrayAdapter);
        Log.d("APP_FILES","APP_FILES***");
        for (String file: addedDocNames) { Log.d("file","file---"+file); }

        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast toast = new Toast(CatalogActivity.this);
                ImageView toastImageView = new ImageView(CatalogActivity.this);
                TextView tw = (TextView) view;
                toastImageView.setImageBitmap(MainActivity.documents.get(tw.getText()).getBitmap());
                toast.setView(toastImageView);
                toast.show();

            }
        };
        listView.setOnItemClickListener(itemListener);

    }


}
