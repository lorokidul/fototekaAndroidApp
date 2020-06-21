package com.sna.fototeka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
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

    private static ArrayList<SliderItem> getSliderItems(List<Page>  pages) {

       ArrayList<SliderItem> sliderItems = new ArrayList<SliderItem>();

        for (Page page : pages) { sliderItems.add(new SliderItem(page.filename)); }

        return sliderItems;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        String documentName = getIntent().getStringExtra("doc");
        getSupportActionBar().setTitle(documentName);
        SelectPagesFromDatabase selectPages = new SelectPagesFromDatabase();
        selectPages.execute(documentName);
    }

    public class SelectPagesFromDatabase extends AsyncTask<String,Void, List<Page>> {


        @Override
        protected List<Page> doInBackground(String ...params) {
            PageDao pageDao = App.getInstance().getDatabase().pageDao();
            String docName = params[0];
            return  pageDao.getFilesByDocName(docName);
        }

        @Override
        protected void onPostExecute(List<Page> pages) {
            super.onPostExecute(pages);
            setContentView(R.layout.activity_display_pages);

            viewPager2 = findViewById(R.id.viewPagerImagesSlider);
            List<SliderItem> sliderItems = getSliderItems(pages);
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
}
