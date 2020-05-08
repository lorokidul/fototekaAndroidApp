package com.sna.fototeka;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SliderItem {

    private String image;
    private Bitmap bitmap;
    SliderItem(String image){ this.image = image;}

    public String getImage(){return image;}
    public Bitmap getBitmap(Context context){
        FileInputStream file = null;
        try {
            file = context.openFileInput(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        bitmap = BitmapFactory.decodeStream(file);
        return bitmap;
    }
}
