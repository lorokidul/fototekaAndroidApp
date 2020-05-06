package com.sna.fototeka;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class PhotoActivity extends AppCompatActivity {
    ImageView imageView;
    Button takePicBtn, saveDocButton, addPageBtn;
    int pageCounter = 0;
    byte[] docPicByteArray;
    ArrayList<String> pages = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
         pages = new ArrayList<String>();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_photo);
        final String docName = getIntent().getStringExtra("docName");
        final String filename = getIntent().getStringExtra("filename");
        MainActivity.documents.put(docName, new Document(docName, new ArrayList<String>(), null, null));

        imageView = (ImageView) findViewById(R.id.savedImageView);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra("docName",docName);
        intent.putExtra("docName",filename);
        intent.putExtra("pageNumber",pageCounter);

        startActivityForResult(intent, 0);
        takePicBtn = (Button) findViewById(R.id.remakeButton);
        saveDocButton = (Button) findViewById(R.id.saveDocButton);
        addPageBtn = (Button) findViewById(R.id.addPageButton);
        View.OnClickListener onClickListenerTakePic = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);




            }
        };

        View.OnClickListener onClickListenerAddPage = new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);

                pageCounter++;

            }
        };

        View.OnClickListener onClickListenerSaveDoc = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.documents.put(docName, new Document(docName, pages, docPicByteArray, ((BitmapDrawable) imageView.getDrawable()).getBitmap()));
                Intent intent = new Intent(PhotoActivity.this, DisplaySavedActivity.class);
                intent.putExtra("docName", docName);
                intent.putExtra("docPic", docPicByteArray);
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

        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
        String pageName = getIntent().getStringExtra("docName") + DocumentsHelper.separator + pageCounter + ".png";
        pages.add(pageName);
        byte[] byteArray = bStream.toByteArray();
        docPicByteArray = byteArray.clone();
        try {
            PhotoActivity.this.openFileOutput(pageName, Context.MODE_PRIVATE).write(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
        pageCounter++;


    }
}
