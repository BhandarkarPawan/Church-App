package com.pawanbhandarkar.android.churchapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by Pawan on 21-10-2017.
 */

public class EGHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 8;
    private static final String DATABASE_NAME = "Songs";
    private String TABLE_NAME = "Entries";
    private final String KEY_ID = "ID";
    private final String SONG_NAME = "Songname";
    private final String CATEGORY = "Category";
    private HashSet<String> SongNameSet = new HashSet<>();
    private ArrayList<String> SongNameList = new ArrayList<>();
    private int count = 0;

    public EGHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db2) {
        {

            {


                String createTable = "CREATE TABLE " + TABLE_NAME + " ( "
                        + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + SONG_NAME + " TEXT, "
                        + CATEGORY + " TEXT )";
                db2.execSQL(createTable);
            }

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db2, int oldVersion, int newVersion) {

        db2.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db2);


    }

    public void addData(Context context, String SongName, String category) {

        SQLiteDatabase db2 = this.getWritableDatabase();

        String selectQuery = "SELECT " + SONG_NAME + " FROM " + TABLE_NAME + " WHERE " + CATEGORY + " = \"" +category +"\"";
        Cursor cursor = db2.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            count=0;
            do {

                SongNameSet.add(cursor.getString(0));
                SongNameList.add(count,cursor.getString(0));
                count++;

            } while (cursor.moveToNext());
        }
        if(!SongNameSet.contains(SongName)) {
            SongNameSet.add(SongName);

            //Log.v("SongsHelper", "getWritable works ");
            ContentValues contentValues = new ContentValues();
            //Log.v("SongsHelper", "contentValues Created");
            contentValues.put(SONG_NAME, SongName);
            //Log.v("SongsHelper", "SongName Added to contentValues");
            contentValues.put(CATEGORY, category);
            //Log.v("SongsHelper", "Lyrics Added to contentValues");
            db2.insert(TABLE_NAME, null, contentValues);
            //Log.v("SongsHelper", "contentValues Inserted into db2");
            db2.close();
            //Log.v("SongsHelper", "Everything is fine");
        }

    }

    public String generateRandom(String category)
    {

        SQLiteDatabase db2 = this.getWritableDatabase();

        String selectQuery = "SELECT " + SONG_NAME + " FROM " + TABLE_NAME + " WHERE " + CATEGORY + " = \"" +category +"\"" ;
        Cursor cursor = db2.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            count=0;
            do {

                SongNameSet.add(cursor.getString(0));
                SongNameList.add(count,cursor.getString(0));
                count++;

            } while (cursor.moveToNext());
        }
        Random random = new Random();
        int c = random.nextInt(count);
        return SongNameList.get(c).toString();
    }


}


