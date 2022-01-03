package com.tanvircodder.exmple.roomtanvir.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.tanvircodder.exmple.roomtanvir.dao.WordDao;
import com.tanvircodder.exmple.roomtanvir.model.Word;

@Database(entities = {Word.class}, version = 1)
public abstract class WordDatabase extends RoomDatabase {
//    createing a abstract method of the dao interface
    public abstract WordDao wordDao();
}
