package com.sna.fototeka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class DisplaySavedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.documentCreation);
        setContentView(R.layout.activity_display_saved);
        String docName = getIntent().getStringExtra("docName");
        TextView textView = findViewById(R.id.savedTextView);
        textView.setText("Документ " + docName + " сохранен!");
        Button addDocBtn = findViewById(R.id.addMoreDocButton);
        Button goToCatalogBtn = findViewById(R.id.goToCatalogButton);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplaySavedActivity.this, FillDataActivity.class);
                startActivity(intent);
                finish();
            }
        };
        View.OnClickListener onClickListenerGoBtn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplaySavedActivity.this, CatalogActivity.class);
                startActivity(intent);
            }
        };
        addDocBtn.setOnClickListener(onClickListener);
        goToCatalogBtn.setOnClickListener(onClickListenerGoBtn);
        FinDocPic findDocPic = new FinDocPic();
        findDocPic.execute(docName);

    }

    class FinDocPic extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... params) {
            PageDao pageDao = App.getInstance().getDatabase().pageDao();
            List<Page> pages = pageDao.getFilesByDocName(params[0]);
            Log.d("PAGES", params[0] +"|"+ Integer.toString(pages.size() ));
            if(pages.size()>0){

                String filename = pages.get(0).filename;

                Bitmap bitmap = BitmapFactory.decodeFile(filename);
                Matrix matrix = PhotoFunctions.getRotationMatrix(filename);
                Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);


                return rotatedBitmap;

            }else{
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            ImageView imageView = findViewById(R.id.savedImageView);
            imageView.setImageBitmap(bitmap);
        }
    }
}
