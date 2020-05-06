package com.sna.fototeka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;


public class FillDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fill_data);

        Button addPagesBtn = (Button) findViewById(R.id.buttonAddPages);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(FillDataActivity.this, PhotoActivity.class);
                EditText editText = (EditText) findViewById(R.id.nameEditBox);
                String docName = editText.getText().toString();
                if(MainActivity.documents.containsKey(docName)){
                    Toast.makeText(FillDataActivity.this, "Документ с таким наименованием уже есть." , Toast.LENGTH_LONG).show();
                } else {
                    intent.putExtra("docName", docName);
                    intent.putExtra("filename", docName + ".png");
                    startActivity(intent);
                    finish();
                }
            }
        };

        addPagesBtn.setOnClickListener(onClickListener);



    }
}
