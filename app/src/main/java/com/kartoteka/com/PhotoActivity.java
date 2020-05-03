package com.kartoteka.com;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class PhotoActivity extends AppCompatActivity {
    ImageView imageView;
    Button takePicBtn,okBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        imageView = (ImageView) findViewById(R.id.imageView);

        Intent intent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,0);
        takePicBtn = (Button)findViewById(R.id.remakeButton);
        okBtn = (Button)findViewById(R.id.okButton);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);

            }
        };
        takePicBtn.setOnClickListener(onClickListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);
    }
}
