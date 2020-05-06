package com.sna.fototeka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CatalogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(fileList().length == 0) {
            setContentView(R.layout.catalog_empty);
            Button addDocBtn = findViewById(R.id.addDocButton);
            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CatalogActivity.this, PhotoActivity.class);
                    startActivity(intent);
                    finish();
                }
            };
            addDocBtn.setOnClickListener(onClickListener);
        } else {
            setContentView(R.layout.activity_catalog);


            ListView listView = (findViewById(R.id.simpleListView));
            ArrayList<String> addedDocNames = new ArrayList<String>(MainActivity.documents.keySet());
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, addedDocNames);
            listView.setAdapter(arrayAdapter);
            Log.d("APP_FILES", "APP_FILES***");
            for (String file : addedDocNames) {
                Log.d("file", "file---" + file);
            }

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


}
