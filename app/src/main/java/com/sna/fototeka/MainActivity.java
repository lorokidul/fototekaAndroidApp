package com.sna.fototeka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button addDocButton = findViewById(R.id.addDocumentButton);
        Button goToCatalogBtn = findViewById(R.id.goToCatalogFromMainButton);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FillDataActivity.class);
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
