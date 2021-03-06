package com.sna.fototeka;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PageDao {

    @Query("SELECT * FROM page")
    List<Page> getAll();

    @Query("SELECT * FROM page WHERE id = :id")
    List<Page> getById(long id);

    @Query("SELECT * FROM page WHERE document== :doc")
    List<Page> getFilesByDocName(String doc);


    @Insert
    long insert(Page page);

    @Update
    void update(Page page);

    @Query("UPDATE page SET filename =:fname WHERE id==:pageId")
    void updateFileByKey(int pageId, String fname);

    @Delete
    void delete(Page page);


    @Query("DELETE FROM page WHERE document== :doc")
    void deletePagesOfDoc(String doc);

    @Query("DELETE FROM page")
    void deleteAll();
}