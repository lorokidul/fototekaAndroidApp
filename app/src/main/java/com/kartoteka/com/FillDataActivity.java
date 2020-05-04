package com.kartoteka.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kartoteka.com.R;



public class FillDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final byte[] byteArray = getIntent().getByteArrayExtra("docPic");

        setContentView(R.layout.activity_fill_data);

        Button saveButon = (Button) findViewById(R.id.saveButton);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FillDataActivity.this, DisplaySavedActivity.class);
                EditText editText = (EditText) findViewById(R.id.nameEditBox);
                intent.putExtra("docName",editText.getText().toString());
                intent.putExtra("docPic",byteArray);
                startActivity(intent);
                finish();
            }
        };

        saveButon.setOnClickListener(onClickListener);



    }
}
