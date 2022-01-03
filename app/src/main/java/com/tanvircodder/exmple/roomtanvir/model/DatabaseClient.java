package com.tanvircodder.exmple.roomtanvir.model;

import android.content.Context;

import androidx.room.Room;

import com.tanvircodder.exmple.roomtanvir.database.WordDatabase;

public class DatabaseClient {
    private Context context;
    private static DatabaseClient mInstance;
    private WordDatabase appDatabase;

    public DatabaseClient(Context context) {
        this.context = context;
        this.appDatabase = Room.databaseBuilder(context,WordDatabase.class,"myWord")
                .build();
    }

    public synchronized static DatabaseClient getmInstance(Context context) {
        if (mInstance == null){
            mInstance = new DatabaseClient(context);
        }
        return mInstance;
    }

    public WordDatabase getAppDatabase() {
        return appDatabase;
    }
}
