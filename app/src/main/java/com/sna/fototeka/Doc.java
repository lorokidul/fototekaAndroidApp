package com.sna.fototeka;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class Doc {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public int numberOfPages;
    public String category;


}