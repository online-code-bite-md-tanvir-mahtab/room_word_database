package com.tanvircodder.exmple.roomtanvir.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.tanvircodder.exmple.roomtanvir.model.Word;

import java.util.List;

@Dao
public interface WordDao {

    @Query("SELECT * FROM word")
    List<Word> getAllWrods();

    @Insert
    void insert(Word word);
    @Delete
    void delete(Word word);
    @Update
    void update(Word word);
}
