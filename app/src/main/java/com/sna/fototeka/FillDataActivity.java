package com.sna.fototeka;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class FillDataActivity extends AppCompatActivity {
    private int docId = 0;

    public void setDocId(int id) {
        docId = id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fill_data);

        Button addPagesBtn = findViewById(R.id.buttonAddPages);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.nameEditBox);
                String docName = editText.getText().toString();
                SelectDocsFromDatabase selectDoscTask = new SelectDocsFromDatabase(getApplicationContext());
                selectDoscTask.execute(docName);

            }

        };
        addPagesBtn.setOnClickListener(onClickListener);
    }
}
