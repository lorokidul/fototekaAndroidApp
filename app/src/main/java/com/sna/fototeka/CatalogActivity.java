package com.sna.fototeka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CatalogActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final int VERTICAL_ITEM_SPACE = 48;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        recyclerView = (RecyclerView) findViewById(R.id.docsList);
        Log.d("recycler","docsList "+recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(CatalogActivity.this));
        recyclerView.setAdapter(new Adapter(CatalogActivity.this, MainActivity.documents.keySet().toArray(new String[MainActivity.documents.size()])));
        recyclerView.addItemDecoration(new DividerItemDecoration(CatalogActivity.this, DividerItemDecoration.VERTICAL));
        ;
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));

    }
}
