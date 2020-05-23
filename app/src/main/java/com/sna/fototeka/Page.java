package com.sna.fototeka;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Page {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String document;
    public String filename;
    public int pageNumber;

    @ColumnInfo(name = "doc_id")
    public int docId;


}
