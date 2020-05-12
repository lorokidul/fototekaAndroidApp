package com.sna.fototeka;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class DocumentWithFiles {
    @Embedded public Doc doc;
    @Relation(
            parentColumn = "id",
            entityColumn = "doc_id"
    )

    public List<Page> pages;
}
