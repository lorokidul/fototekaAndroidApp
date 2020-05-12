package com.sna.fototeka;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Page.class, Doc.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PageDao pageDao();
    public abstract DocDao docDao();
}