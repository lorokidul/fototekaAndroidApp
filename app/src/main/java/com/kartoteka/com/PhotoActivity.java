package com.kartoteka.com;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class PhotoActivity extends AppCompatActivity {
    ImageView imageView;
    Button takePicBtn,okBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        imageView = (ImageView) findViewById(R.id.savedImageView);

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

        View.OnClickListener onClickListenerOk = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (PhotoActivity.this, FillDataActivity.class);

                ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                Bitmap bitmap =((BitmapDrawable)imageView.getDrawable()).getBitmap();
                bitmap.compress(Bitmap.CompressFormat.PNG,100, bStream);
                byte[] byteArray = bStream.toByteArray();
                intent.putExtra("docPic", byteArray);
                startActivity(intent);
                finish();

            }
        };
        takePicBtn.setOnClickListener(onClickListener);
        okBtn.setOnClickListener(onClickListenerOk);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);

    }
}
