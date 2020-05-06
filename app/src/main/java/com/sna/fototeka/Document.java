package com.sna.fototeka;

import android.graphics.Bitmap;

public class Document {
    String name;
    String filename;
    byte[] bArray;
    Bitmap bitmap;

    Document(String name, String filename, byte[] bArray, Bitmap bitmap){
        this.name=name;
        this.filename = filename;
        this.bArray = bArray;
        this.bitmap = bitmap;
    }

    public String getName(){return name;}
    public String getFilename(){return filename;}
    public Bitmap getBitmap() {return bitmap;}

}
