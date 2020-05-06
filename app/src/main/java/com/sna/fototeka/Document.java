package com.sna.fototeka;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Document {
    String name;
    ArrayList<String> filenames;
    byte[] bArray;
    Bitmap bitmap;

    Document(String name, ArrayList<String> filenames, byte[] bArray, Bitmap bitmap){
        this.name=name;
        this.filenames = filenames;
        this.bArray = bArray;
        this.bitmap = bitmap;
    }

    public String getName(){return name;}
    public String getFilenames(){return filenames.get(0);}
    public Bitmap getBitmap() {return bitmap;}

}
