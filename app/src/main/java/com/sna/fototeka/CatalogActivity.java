package com.sna.fototeka;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
        SelectDocWithFiles selectDocs = new SelectDocWithFiles();
        selectDocs.execute();
        }

    class SelectDocWithFiles extends AsyncTask<String,Void, List<DocumentWithFiles>> {

        @Override
        protected List<DocumentWithFiles> doInBackground(String... params) {
            AppDatabase db = App.getInstance().getDatabase();
            List<DocumentWithFiles> docsList = db.docDao().getDocWithFiles();
            return docsList;
        }

        @Override
        protected void onPostExecute(List<DocumentWithFiles> docsList) {
            super.onPostExecute(docsList);
            if (docsList.size() == 0) {
                setContentView(R.layout.catalog_empty);
                Button addDocButton = findViewById(R.id.addDocButton);
                View.OnClickListener onClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CatalogActivity.this, FillDataActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                };
                addDocButton.setOnClickListener(onClickListener);
            } else {
                setContentView(R.layout.activity_catalog);

                recyclerView = findViewById(R.id.docsList);
                recyclerView.setLayoutManager(new LinearLayoutManager(CatalogActivity.this));
                recyclerView.setAdapter(new Adapter(CatalogActivity.this, docsList));
                recyclerView.addItemDecoration(new DividerItemDecoration(CatalogActivity.this, DividerItemDecoration.VERTICAL));
                recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
            }
        }
    }

}

