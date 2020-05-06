package com.sna.fototeka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    public static HashMap<String, Document> documents = new HashMap<String,Document>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //for (String f : fileList()){deleteFile(f);}
        DocumentsHelper.fillOnStart(MainActivity.this, fileList());
        super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_main);

            Button addDocButton = findViewById(R.id.addDocumentButton);
            Button goToCatalogBtn = (Button) findViewById(R.id.goToCatalogFromMainButton);

            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, PhotoActivity.class);
                    startActivity(intent);
                }
            };
            addDocButton.setOnClickListener(onClickListener);

            View.OnClickListener onClickListenerGoBtn = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, CatalogActivity.class);
                    startActivity(intent);
                }
            };
            goToCatalogBtn.setOnClickListener(onClickListenerGoBtn);
        }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("destroy_msg", "quit");
    }
}