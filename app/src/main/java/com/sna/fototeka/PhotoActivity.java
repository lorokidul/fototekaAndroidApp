package com.sna.fototeka;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Dao;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class PhotoActivity extends AppCompatActivity {
    ImageView imageView;
    Button takePicBtn, saveDocButton, addPageBtn;
    int pageCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        final String docName = getIntent().getStringExtra("docName");
        final String filename = getIntent().getStringExtra("filename");

        imageView = findViewById(R.id.savedImageView);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra("docName", docName);
        intent.putExtra("docName", filename);
        intent.putExtra("pageNumber", pageCounter);

        startActivityForResult(intent, 0);

        takePicBtn = findViewById(R.id.remakeButton);
        saveDocButton = findViewById(R.id.saveDocButton);
        addPageBtn = findViewById(R.id.addPageButton);

        View.OnClickListener onClickListenerTakePic = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
            }
        };

        View.OnClickListener onClickListenerAddPage = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 2);
            }
        };

        View.OnClickListener onClickListenerSaveDoc = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhotoActivity.this, DisplaySavedActivity.class);
                intent.putExtra("docName", docName);
                startActivity(intent);
            }


        };

        takePicBtn.setOnClickListener(onClickListenerTakePic);
        addPageBtn.setOnClickListener(onClickListenerAddPage);
        saveDocButton.setOnClickListener(onClickListenerSaveDoc);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

            super.onActivityResult(requestCode, resultCode, data);
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
            String docName = getIntent().getStringExtra("docName");
            String pageName = docName + "_" + pageCounter + ".png";
            Page page = new Page();
            page.id = fileList().length;
            page.document = docName;
            page.filename = pageName;
            page.pageNumber = pageCounter;
            page.docId = getIntent().getIntExtra("docId", 0);

        if(requestCode==1){

            UpdatePageInDatabase updatePage = new UpdatePageInDatabase(getApplicationContext());
            updatePage.execute(page);

        } else {
            ByteArrayOutputStream bStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);

            byte[] byteArray = bStream.toByteArray();
            try (FileOutputStream output = openFileOutput(pageName, Context.MODE_PRIVATE)) {
                output.write(byteArray);
            } catch (IOException e) {
                e.printStackTrace();
            }

            InsertPageToDatabase insertPage = new InsertPageToDatabase(getApplicationContext());
            insertPage.execute(page);
            pageCounter++;

        }



    }
}
