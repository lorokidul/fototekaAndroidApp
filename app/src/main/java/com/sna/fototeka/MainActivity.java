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
        DocumentsHelper.fillOnStart(MainActivity.this, fileList());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TAG","hi, this is kartoteka");
        Button addDocButton = findViewById(R.id.addDocumentButton);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ADD","add doc button pushed.");
                Intent intent = new Intent(MainActivity.this, PhotoActivity.class);
                startActivity(intent);
            }
        };
        addDocButton.setOnClickListener(onClickListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("destroy_msg", "quit");
    }
}
