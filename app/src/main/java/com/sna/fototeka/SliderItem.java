package com.sna.fototeka;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SliderItem {

    private String image;
    private Bitmap bitmap;
    SliderItem(String image){ this.image = image;}

    public String getImage(){return image;}
    public Bitmap getBitmap(Context context){

        bitmap = BitmapFactory.decodeFile(image);
        Matrix matrix = PhotoFunctions.getRotationMatrix(image);
        Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return rotatedBitmap;
    }
}
