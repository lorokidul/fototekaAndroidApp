package com.sna.fototeka;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DocDao {

    @Query("SELECT * FROM doc")
    List<Doc> getAll();

    @Query("SELECT * FROM doc WHERE id = :id")
    Doc getById(long id);


    @Query("SELECT * FROM doc WHERE name = :name")
   List<Doc> getDocsByName(String name);

    @Query("SELECT * FROM doc WHERE category == category")
    List<Doc> getDocWithCategory();

    @Query("SELECT id, name from doc ")
    List<DocumentWithFiles> getDocWithFiles();

    @Query("SELECT id, name from doc  WHERE name==:name")
    List<DocumentWithFiles> getDocWithFilesByName(String name);

    @Query("SELECT id, name from doc WHERE  category == :category")
    List<DocumentWithFiles> getDocWithFilesByCategory(String category);

    @Insert
    void insert(Doc doc);

    @Update
    void update(Doc doc);

    @Delete
    void delete(Doc doc);

    @Query("DELETE FROM doc")
    void deleteAll();

}
