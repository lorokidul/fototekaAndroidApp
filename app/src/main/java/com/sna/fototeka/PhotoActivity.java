package com.sna.fototeka;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.room.Dao;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class PhotoActivity extends AppCompatActivity {
    ImageView imageView;
    Button takePicBtn, saveDocButton, addPageBtn;
    String docName;
    int pageCounter = 1;
    public static final int CODE_ADD = 1000;
    public static final int CODE_REMAKE = 1001;
    public static final int CODE_START = 1002;
    public int pageId;
    Uri photoUri;

    String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents

        currentPhotoPath = image.getAbsolutePath();
        return image;
    }



    private void dispatchTakePictureIntent(int REQUEST_CODE, String docName, int pageCounter) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePictureIntent.putExtra("docName", docName);
        takePictureIntent.putExtra("pageNumber", pageCounter);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.sna.fototeka.fileprovider",
                        photoFile);
                takePictureIntent.putExtra("photoUri", photoURI.toString());
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                photoUri = photoURI;


                startActivityForResult(takePictureIntent, REQUEST_CODE);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.documentCreation);
        setContentView(R.layout.activity_photo);
        docName = getIntent().getStringExtra("docName");
        final String filename = getIntent().getStringExtra("filename");

        imageView = findViewById(R.id.savedImageView);


        dispatchTakePictureIntent(CODE_START, docName, pageCounter);
        takePicBtn = findViewById(R.id.remakeButton);
        saveDocButton = findViewById(R.id.saveDocButton);
        addPageBtn = findViewById(R.id.addPageButton);

        View.OnClickListener onClickListenerTakePic = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent(CODE_REMAKE, docName, pageCounter);
            }
        };

        View.OnClickListener onClickListenerAddPage = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent(CODE_ADD, docName, pageCounter);

            }
        };

        View.OnClickListener onClickListenerSaveDoc = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhotoActivity.this, DisplaySavedActivity.class);
                intent.putExtra("docName", docName);
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

        File file = new File(currentPhotoPath);

        Bitmap rotatedBitmap = null;
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.fromFile(file));
            Matrix matrix = PhotoFunctions.getRotationMatrix(currentPhotoPath);
            rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

        } catch (IOException e) {
            e.printStackTrace();
        }
        if(rotatedBitmap.getByteCount()>PhotoFunctions.MAX_BITMAP_SIZE){
            rotatedBitmap = PhotoFunctions.createScaledBitmap(rotatedBitmap);
        }
        imageView.setImageBitmap(rotatedBitmap);

        Page page = new Page();

        if (requestCode == CODE_ADD) {
            pageCounter++;
        }
        String pageName = docName + "_" + pageCounter + ".png";

        page.document = docName;
        page.filename = currentPhotoPath;
        page.pageNumber = pageCounter;
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        byte[] byteArray = bStream.toByteArray();

        try (FileOutputStream output = openFileOutput(pageName, Context.MODE_PRIVATE)) {
            output.write(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (requestCode == CODE_REMAKE) {
            page.id=pageId;
            UpdatePageInDatabase updatePage = new UpdatePageInDatabase(getApplicationContext());
            updatePage.execute(page);

        } else {

            InsertPageToDatabase insertPage = new InsertPageToDatabase();
            insertPage.execute(page);

        }
    }

class InsertPageToDatabase extends AsyncTask<Page, Void, Integer> {

    @Override
    protected Integer doInBackground(Page... params) {
        Page page = params[0];
        AppDatabase db = App.getInstance().getDatabase();
        PageDao pageDao = db.pageDao();
        DocDao docDao = db.docDao();

        Doc doc = docDao.getDocsByName(page.document).get(0);
        doc.numberOfPages=page.pageNumber;
        docDao.update(doc);
        Integer key = (int) pageDao.insert(page);
        return key;
    }

    @Override
    protected void onPostExecute(Integer key) {
        super.onPostExecute(key);
        pageId = key;


    }
}

}
