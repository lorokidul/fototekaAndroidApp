package com.kartoteka.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
