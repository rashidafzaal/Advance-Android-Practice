package com.example.roomdatabase.Room;

import android.content.Context;

import androidx.room.Room;

public class DatabaseSingletonClient {

    Context thisContext;
    private AppDatabase appDatabase;
    private static DatabaseSingletonClient mInstance;

    private DatabaseSingletonClient(Context ctx)
    {
        this.thisContext = ctx;
        appDatabase = Room.databaseBuilder(ctx, AppDatabase.class, "MyToDos").allowMainThreadQueries().build();
    }

    public static synchronized DatabaseSingletonClient getInstance(Context ctx)
    {
        if (mInstance == null) {
            mInstance = new DatabaseSingletonClient(ctx);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
