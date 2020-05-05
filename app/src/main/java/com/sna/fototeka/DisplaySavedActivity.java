package com.sna.fototeka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        Button addDocBtn = (Button)findViewById(R.id.addMoreDocButton);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplaySavedActivity.this, PhotoActivity.class);
                startActivity(intent);
                finish();
            }
        };
        addDocBtn.setOnClickListener(onClickListener);
    }
}
