package com.sna.fototeka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CatalogActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final int VERTICAL_ITEM_SPACE = 48;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppDatabase db = App.getInstance().getDatabase();
        if (db.docDao().getAll().size()==0) {
            setContentView(R.layout.catalog_empty);
            Button addDocButton = findViewById(R.id.addDocButton);
            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CatalogActivity.this, FillDataActivity.class);
                    startActivity(intent);
                }
            };
            addDocButton.setOnClickListener(onClickListener);
        } else {
            setContentView(R.layout.activity_catalog);
            recyclerView = (RecyclerView) findViewById(R.id.docsList);


            List<DocumentWithFiles> docsList = db.docDao().getDocWithFiles();

            recyclerView.setLayoutManager(new LinearLayoutManager(CatalogActivity.this));

            recyclerView.setAdapter(new Adapter(CatalogActivity.this, docsList));
            recyclerView.addItemDecoration(new DividerItemDecoration(CatalogActivity.this, DividerItemDecoration.VERTICAL));
            ;
            recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        }
    }
}
