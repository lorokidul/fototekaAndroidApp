package com.sna.fototeka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class DisplayPages extends AppCompatActivity {
    private ViewPager2 viewPager2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_pages);
        String documentName = getIntent().getStringExtra("doc");
        viewPager2 = findViewById(R.id.viewPagerImagesSlider);

        Document doc = MainActivity.documents.get(documentName);
        Log.d("DOC","DOC " +doc);
        Log.d("DOC FILES", "DF"+doc.getFilenames());
        Log.d("DOC FILES", "DSI items");
        for(SliderItem i : doc.getSliderItems())  {Log.d("FILE CONTENT","file"+i.getImage()+" bm size " +i.getBitmap(DisplayPages.this));}
        Log.d("FILES","FILES");
        for(String f : fileList())  {Log.d("FILE CONTENT","file "+f);}

        List<SliderItem> sliderItems = doc.getSliderItems();
        viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2, DisplayPages.this));



    }
}
