package ru.kpfu.gareevalbert.scheduler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class db_Helper_goals extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "goalsdb";
    public static final String TABLE_NAME = "goal";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";

    public db_Helper_goals(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + TABLE_NAME + "(" + KEY_ID + " integer primary key," + KEY_NAME + " text)" );
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int NewVersion){
        db.execSQL("drop table if exists " + TABLE_NAME);

        onCreate(db);
    }


}
