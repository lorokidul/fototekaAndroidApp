package com.kartoteka.com;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplaySavedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_saved);
        String docName = getIntent().getStringExtra("docName");
        byte[] byteArray = getIntent().getByteArrayExtra("docPic");
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
        ImageView imageView = (ImageView)findViewById(R.id.savedImageView);
        imageView.setImageBitmap(bitmap);
        TextView  textView = (TextView)findViewById(R.id.savedTextView);
        textView.setText("Документ " +docName+" сохранен!");
    }
}
