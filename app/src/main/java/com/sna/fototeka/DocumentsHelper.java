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
import java.util.ArrayList;

public abstract class DocumentsHelper {
    public static String separator = "_";
    private static Bitmap getBitmap(String filename, Context context){
        FileInputStream file = null;
        Bitmap bitmap = null;
        try {
            file = context.openFileInput(filename);
            bitmap = BitmapFactory.decodeStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    private static byte[] getByteArray(String filename, Context context){
        FileInputStream file = null;
        byte[] byteArray = null;
        try {
            file = context.openFileInput(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            byteArray = new byte[(int) file.getChannel().size()];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArray;
    }
    public static void fillOnStart(Context context, String[] filenames) {
        int pos = 0;
        ArrayList<String> addedDocs = new ArrayList<String>();
        String docName = "";
        while (pos < filenames.length) {
            String fName = filenames[pos];
            docName = fName.substring(0,fName.lastIndexOf(separator));
            ArrayList<String> pages = new ArrayList<String>();
            if (!MainActivity.documents.containsKey(docName)) {
                for (int i = pos; i < filenames.length; i++) {
                    String filename = filenames[i];
                    try {
                        String currDocName = filename.substring(0, fName.lastIndexOf(separator));

                        if (currDocName.equals(docName)) {
                            try {
                                FileInputStream file = context.openFileInput(filename);
                                byte[] byteArray = new byte[(int) file.getChannel().size()];
                                Bitmap bitmap = BitmapFactory.decodeStream(file);

                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            pages.add(filename);
                        }
                    } catch(Exception e){
                        e.printStackTrace();
                    }

                }


            }
            MainActivity.documents.put(docName, new Document(docName, pages, getByteArray(pages.get(0),context), getBitmap(pages.get(0),context )));
            pos++;
        }

    }
}

