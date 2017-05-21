package ru.kpfu.gareevalbert.scheduler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class db_Helper_goals extends SQLiteOpenHelper {

    public static final int DATABASE_Version = 1;
    public static final String DATABASE_NAME = "conDB";
    public static final String TABEL_GOALS = "Goals_table";

    public static final String KEY_ID = "_id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_BODY = "body";

    public db_Helper_goals(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABEL_GOALS + "(" + KEY_ID
                + " integer primary key," + KEY_TITLE + " text," + KEY_BODY + " text" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABEL_GOALS);

        onCreate(db);

    }
}
