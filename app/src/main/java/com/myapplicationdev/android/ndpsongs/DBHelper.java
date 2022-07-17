package com.myapplicationdev.android.ndpsongs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.time.Year;
import java.util.ArrayList;

import static java.text.NumberFormat.Field.INTEGER;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ndp.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_SONG = "song";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_SINGER = "singers";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_STAR = "stars";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSongTableSql = "CREATE TABLE " + TABLE_SONG + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT," +
                COLUMN_SINGER + "TEXT1," + COLUMN_YEAR + "TEXT2" + COLUMN_STAR + "TEXT3)";
        db.execSQL(createSongTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONG);
        db.execSQL("ALTER TABLE " + TABLE_SONG + " ADD COLUMN module_name TEXT ");
        db.execSQL("ALTER TABLE " + TABLE_SONG + " ADD COLUMN module_name TEXT1 ");
        db.execSQL("ALTER TABLE " + TABLE_SONG + " ADD COLUMN module_name TEXT2 ");
        db.execSQL("ALTER TABLE " + TABLE_SONG + " ADD COLUMN module_name TEXT3 ");

    }

    public long insertSong(String title, String singers, String year, int stars) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_SINGER, singers);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_STAR, stars);
        long result = db.insert(TABLE_SONG, null, values);
        db.close();
        Log.d("SQL Insert", "ID:" + result); //id returned, shouldn’t be -1
        return result;

    }

    public ArrayList<Songs> getAllSong() {
        ArrayList<Songs> songs = new ArrayList<Songs>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGER, COLUMN_YEAR, COLUMN_STAR};
        Cursor cursor = db.query(TABLE_SONG, columns, null, null,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                String year = cursor.getString(3);
                int stars = cursor.getInt(4);
                Songs song = new Songs(id, title, singers, year, stars);
                songs.add(song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }

    public ArrayList<Songs> getAllSongs(String keyword) {
        ArrayList<Songs> songs = new ArrayList<Songs>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {COLUMN_ID, COLUMN_TITLE,COLUMN_YEAR,COLUMN_SINGER,COLUMN_STAR};
        String condition = COLUMN_STAR + " Like ?";    //? represents the argument below
        String[] args = {"%" + keyword + "%"};
        Cursor cursor = db.query(TABLE_SONG, columns, condition, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                String year = cursor.getString(3);
                Integer stars = cursor.getInt(4);
                Songs song = new Songs(id, title, singers,year,stars);
                songs.add(song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }

    public int updateSong(Songs data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, data.getTitle());
        values.put(COLUMN_SINGER,data.getSingers());
        values.put(COLUMN_YEAR, data.getYear());
        values.put(COLUMN_STAR,data.getStars());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_SONG, values, condition, args);
        db.close();
        return result;
    }

    public int deleteSong(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_SONG, condition, args);
        db.close();
        return result;
    }

}
    }