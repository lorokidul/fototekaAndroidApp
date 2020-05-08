package com.sna.fototeka;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Document {
    private String name;
    private ArrayList<String> filenames;
    private byte[] bArray;
    private Bitmap bitmap;
    private int numberOfPages;
    private ArrayList<SliderItem> sliderItems = new ArrayList<SliderItem>();

    Document(String name, ArrayList<String> filenames, byte[] bArray, Bitmap bitmap) {
        this.name = name;
        this.filenames = filenames;
        this.bArray = bArray;
        this.bitmap = bitmap;
        this.numberOfPages = filenames.size();
        setSliderItems(filenames);
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getFilenames() {
        return filenames;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    private void setSliderItems(ArrayList<String> filenames) {


        for (String filename : filenames) {
            sliderItems.add(new SliderItem(filename));
        }

    }

    public ArrayList<SliderItem> getSliderItems() {
        return sliderItems;
    }
}
