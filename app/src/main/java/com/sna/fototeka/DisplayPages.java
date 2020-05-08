package com.sna.fototeka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class DisplayPages extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private static final int PAGE_LIMIT = 3;
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

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer(){
            @Override
            public void transformPage(View page, float position){
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f+r*0.15f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);
    }
}
