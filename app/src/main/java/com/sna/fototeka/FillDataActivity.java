package com.sna.fototeka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Dao;


public class FillDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fill_data);

        Button addPagesBtn = findViewById(R.id.buttonAddPages);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FillDataActivity.this, PhotoActivity.class);
                EditText editText = findViewById(R.id.nameEditBox);
                String docName = editText.getText().toString();
                AppDatabase db = App.getInstance().getDatabase();
                if (db.docDao().getDocsByName(docName).size() >0) {
                    Toast.makeText(FillDataActivity.this, "Документ с таким наименованием уже есть.", Toast.LENGTH_LONG).show();
                } else {


                    DocDao docDao = db.docDao();
                    Doc doc = new Doc();
                    doc.id = docDao.getAll().size();
                    doc.name = docName;
                    doc.category = "";
                    doc.numberOfPages = 1;
                    docDao.insert(doc);

                    intent.putExtra("docName", docName);
                    intent.putExtra("filename", docName + ".png");
                    intent.putExtra("docId",doc.id);
                    startActivity(intent);
                    finish();
                }
            }
        };

        addPagesBtn.setOnClickListener(onClickListener);


    }
}
