package com.tanvircodder.exmple.roomtanvir.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Word implements Serializable {

//    now i am going to create some column
//    that will be the word name aas column name
//    primary key which will genarate automatically
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "word_name")
    private String wordName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWordName() {
        return wordName;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }
}
