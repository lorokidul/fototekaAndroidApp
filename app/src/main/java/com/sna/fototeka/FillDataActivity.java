package com.sna.fototeka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sna.fototeka.R;

import java.io.IOException;


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
                String docName = editText.getText().toString();
                intent.putExtra("docName",docName);
                intent.putExtra("docPic",byteArray);
                String filename = docName+".png";

                try {
                    FillDataActivity.this.openFileOutput(filename, Context.MODE_PRIVATE).write(byteArray);
                    Log.d("SAVE FILE","file saved");

                } catch (IOException e) {
                    e.printStackTrace();
                }

                startActivity(intent);
                finish();
            }
        };

        saveButon.setOnClickListener(onClickListener);



    }
}
