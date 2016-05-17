package com.example.denis.fightclub.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Denis on 10.05.2016.
 */
public class DBManipulator {

    public SQLiteDatabase sqLiteDatabase;
    public DatabaseHelper databaseHelper;
    public ContentValues contentValues = new ContentValues();

    public DBManipulator(Context context){
        databaseHelper = new DatabaseHelper(context, "player.db", null, 1);
        sqLiteDatabase = databaseHelper.getReadableDatabase();

    }

    public int[] getAll(){
        int[] s = new int[7];
        Cursor cursor = sqLiteDatabase.query("player",new String[]{DatabaseHelper.PLAYER_STRENGHT,DatabaseHelper.PLAYER_AGILITY,DatabaseHelper.PLAYER_CRIT_MULT,
                DatabaseHelper.PLAYER_CRIT_CHANCE,DatabaseHelper.PLAYER_LUCK,DatabaseHelper.PLAYER_PORTRAIT,DatabaseHelper.PLAYER_POINTS},null,null,null,null,null);
        cursor.moveToFirst();
        s [0] = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PLAYER_STRENGHT));
        s [1] = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PLAYER_AGILITY));
        s [2] = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PLAYER_CRIT_MULT));
        s [3] = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PLAYER_CRIT_CHANCE));
        s [4] = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PLAYER_LUCK));
        s [5] = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PLAYER_PORTRAIT));
        s [6] = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PLAYER_POINTS));
        cursor.close();
        return s;
    }

    public String getName(){
        String name;
        Cursor cursor = sqLiteDatabase.query("player",new String[]{DatabaseHelper.PLAYER_NAME_COLUMN},null,null,null,null,null);
        cursor.moveToFirst();
        name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PLAYER_NAME_COLUMN));
        cursor.close();
        return name;
    }

    public void setName(String name){
        contentValues.put(DatabaseHelper.PLAYER_NAME_COLUMN,name);
        sqLiteDatabase.update("player",contentValues,DatabaseHelper.PLAYER_NAME_COLUMN + "<> ?", new String[]{"null"});
    }

    public int getStrenght(){
        int strenght;
        Cursor cursor = sqLiteDatabase.query("player",new String[]{DatabaseHelper.PLAYER_STRENGHT},null,null,null,null,null);
        cursor.moveToFirst();
        strenght = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PLAYER_STRENGHT));
        cursor.close();
        return strenght;
    }

    public void setStrenght(int strenght){
        contentValues.put(DatabaseHelper.PLAYER_STRENGHT,strenght);
        sqLiteDatabase.update("player",contentValues,DatabaseHelper.PLAYER_STRENGHT + "<> ?", new String[]{"null"});
    }

    public int getAgility(){
        int agility;
        Cursor cursor = sqLiteDatabase.query("player",new String[]{DatabaseHelper.PLAYER_AGILITY},null,null,null,null,null);
        cursor.moveToFirst();
        agility = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PLAYER_AGILITY));
        cursor.close();
        return agility;
    }

    public void setAgility(int agility){
        contentValues.put(DatabaseHelper.PLAYER_AGILITY,agility);
        sqLiteDatabase.update("player",contentValues,DatabaseHelper.PLAYER_AGILITY + "<> ?", new String[]{"null"});
    }

    public int getCritChance(){
        int critChance;
        Cursor cursor = sqLiteDatabase.query("player",new String[]{DatabaseHelper.PLAYER_CRIT_CHANCE},null,null,null,null,null);
        cursor.moveToFirst();
        critChance = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PLAYER_CRIT_CHANCE));
        cursor.close();
        return critChance;
    }

    public void setCritChance(int critChance){
        contentValues.put(DatabaseHelper.PLAYER_CRIT_CHANCE,critChance);
        sqLiteDatabase.update("player",contentValues,DatabaseHelper.PLAYER_CRIT_CHANCE + "<> ?", new String[]{"null"});
    }

    public int getCritMult(){
        int critMult;
        Cursor cursor = sqLiteDatabase.query("player",new String[]{DatabaseHelper.PLAYER_CRIT_MULT},null,null,null,null,null);
        cursor.moveToFirst();
        critMult = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PLAYER_CRIT_MULT));
        cursor.close();
        return critMult;
    }

    public void setCritMult(int critMult){
        contentValues.put(DatabaseHelper.PLAYER_CRIT_CHANCE,critMult);
        sqLiteDatabase.update("player",contentValues,DatabaseHelper.PLAYER_CRIT_MULT + "<> ?", new String[]{"null"});
    }

    public int getLuck(){
        int luck;
        Cursor cursor = sqLiteDatabase.query("player",new String[]{DatabaseHelper.PLAYER_LUCK},null,null,null,null,null);
        cursor.moveToFirst();
        luck = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PLAYER_LUCK));
        cursor.close();
        return luck;
    }

    public void setLuck(int luck){
        contentValues.put(DatabaseHelper.PLAYER_LUCK,luck);
        sqLiteDatabase.update("player",contentValues,DatabaseHelper.PLAYER_LUCK + "<> ?", new String[]{"null"});
    }

    public int getCharacterPortrait(){
        int portrait;
        Cursor cursor = sqLiteDatabase.query("player",new String[]{DatabaseHelper.PLAYER_PORTRAIT},null,null,null,null,null);
        cursor.moveToFirst();
        portrait = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PLAYER_PORTRAIT));
        cursor.close();
        return portrait;
    }

    public void setCharacterPortrait(int portrait){
        contentValues.put(DatabaseHelper.PLAYER_PORTRAIT,portrait);
        sqLiteDatabase.update("player",contentValues,DatabaseHelper.PLAYER_PORTRAIT + "<> ?", new String[]{"null"});
    }

    public void setPoint(int point){
        contentValues.put(DatabaseHelper.PLAYER_POINTS,point);
        sqLiteDatabase.update("player",contentValues,DatabaseHelper.PLAYER_POINTS + "<> ?", new String[]{"null"});
    }

    public int getPoints(){
        int points;
        Cursor cursor = sqLiteDatabase.query("player",new String[]{databaseHelper.PLAYER_POINTS},null,null,null,null,null);
        cursor.moveToFirst();
        points = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PLAYER_POINTS));
        cursor.close();
        return points;
    }
}
