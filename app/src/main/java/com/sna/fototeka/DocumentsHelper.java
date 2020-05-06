package com.sna.fototeka;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.sna.fototeka.MainActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public abstract  class DocumentsHelper {

    public static void fillOnStart(Context context, String[] filenames){
        for (String filename : filenames){
            try {
                FileInputStream file =  context.openFileInput(filename);
                byte[] byteArray = new byte[(int) file.getChannel().size()];
                Bitmap bitmap = BitmapFactory.decodeStream(file);
                String docName = filename.replace(".png","");
                MainActivity.documents.put(docName, new Document(docName,filename,byteArray.clone(),bitmap));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
