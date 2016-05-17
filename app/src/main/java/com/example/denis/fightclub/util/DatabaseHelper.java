package com.example.denis.fightclub.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.example.denis.fightclub.R;

/**
 * Created by Denis on 10.05.2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "player.db";
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_TABLE = "player";

    public static final String PLAYER_NAME_COLUMN = "player_name";
    public static final String PLAYER_STRENGHT = "strenght";
    public static final String PLAYER_AGILITY = "agility";
    public static final String PLAYER_CRIT_CHANCE = "crit_chance";
    public static final String PLAYER_CRIT_MULT = "crit_multipl";
    public static final String PLAYER_LUCK = "luck";
    public static final String PLAYER_PORTRAIT = "portrait";
    public static final String PLAYER_POINTS = "points";


    private static final String DATABASE_CREATE = "create table "
            + DATABASE_TABLE + " (" + BaseColumns._ID
            + " integer primary key autoincrement, " + PLAYER_NAME_COLUMN
            + " text not null, " + PLAYER_STRENGHT + " integer, " + PLAYER_AGILITY
            + " integer, " + PLAYER_CRIT_CHANCE + " integer, " + PLAYER_CRIT_MULT
            + " integer, " + PLAYER_LUCK + " integer, " + PLAYER_PORTRAIT + " integer, " + PLAYER_POINTS + " integer);";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    DatabaseHelper (Context context){
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
        ContentValues values = new ContentValues();
        values.put(PLAYER_NAME_COLUMN,"Player");
        values.put(PLAYER_STRENGHT,0);
        values.put(PLAYER_AGILITY,0);
        values.put(PLAYER_CRIT_CHANCE,0);
        values.put(PLAYER_CRIT_MULT,0);
        values.put(PLAYER_LUCK,0);
        values.put(PLAYER_POINTS,0);
        values.put(PLAYER_PORTRAIT, R.drawable.character1);
        db.insert("player",null,values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
