package com.sna.fototeka;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CatalogActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Adapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final int VERTICAL_ITEM_SPACE = 48;
    private int layoutId = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SelectDocWithFiles selectDocs = new SelectDocWithFiles();
        selectDocs.execute();
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(layoutId != R.id.catalogEmptyLayout) {
            getMenuInflater().inflate(R.menu.docs_menu, menu);
            MenuItem item = menu.findItem(R.id.action_search);
            SearchView searchView = (SearchView) item.getActionView();
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    recyclerAdapter.getFilter().filter(newText);
                    return false;
                }
            });
        }
        return super.onCreateOptionsMenu(menu);
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
                layoutId = R.id.catalogEmptyLayout;
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
                layoutId = R.id.activityCatalogLayout;
                recyclerView = findViewById(R.id.docsList);
                recyclerView.setLayoutManager(new LinearLayoutManager(CatalogActivity.this));
                recyclerAdapter = new Adapter(CatalogActivity.this, docsList);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerView.addItemDecoration(new DividerItemDecoration(CatalogActivity.this, DividerItemDecoration.VERTICAL));
                recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
            }
        }
    }

}

