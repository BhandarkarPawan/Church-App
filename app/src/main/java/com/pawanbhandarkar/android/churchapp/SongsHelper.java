package com.pawanbhandarkar.android.churchapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Pawan on 01-10-2017.
 */

public class SongsHelper extends SQLiteOpenHelper {

    private static final String TAG = "SongsHelper";

    private static final int DATABASE_VERSION=8;
    private static final String DATABASE_NAME = "Songs";
    private String TABLE_NAME = "ChurchSongs";
    private final String KEY_ID = "ID";
    private final String SONG_NAME = "Songname";
    private final String LYRICS = "Lyrics";
    private HashSet<String> SongNamesSet = new HashSet<>();


    public SongsHelper(Context context) {

        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        {


            String createTable = "CREATE TABLE " + TABLE_NAME + " ( "
                    +KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + SONG_NAME + " TEXT, "
                    + LYRICS + " TEXT )";
            db.execSQL(createTable);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);


    }


    public void addData(Context context, String SongName, String Lyrics) {

        SQLiteDatabase db = this.getWritableDatabase();



            //Log.v("SongsHelper", "getWritable works ");
            ContentValues contentValues = new ContentValues();
            //Log.v("SongsHelper", "contentValues Created");
            contentValues.put(SONG_NAME, SongName);
            //Log.v("SongsHelper", "SongName Added to contentValues");
            contentValues.put(LYRICS, Lyrics);
            //Log.v("SongsHelper", "Lyrics Added to contentValues");
            db.insert(TABLE_NAME, null, contentValues);
            //Log.v("SongsHelper", "contentValues Inserted into db");
            db.close();
            //Log.v("SongsHelper", "Everything is fine");
            SongNamesSet.add(SongName);
    }



    public boolean checkForErrors(Context context, String SongName, String Lyrics)
    {

        SQLiteDatabase db = this.getWritableDatabase();


        String selectQuery = "SELECT " + SONG_NAME + " FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                SongNamesSet.add(cursor.getString(0));

            } while (cursor.moveToNext());
        }
        if (SongName.isEmpty()||Lyrics.isEmpty())
        {
            Toast toast = Toast.makeText(context, "Enter both song name AND Lyrics",Toast.LENGTH_SHORT);
            toast.show();
            return false;

        }
        else if(SongNamesSet.contains(SongName))
        {Toast toast = Toast.makeText(context, "Song name exists", Toast.LENGTH_SHORT);
            toast.show();
            return false;}

        else return true;

    }

    public ArrayList<String> getSongNameList()
    {
        ArrayList<String> SongNamesList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        String selectQuery = "SELECT " + SONG_NAME + " FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                SongNamesList.add(cursor.getString(0));

            } while (cursor.moveToNext());
        }
        return SongNamesList;
    }

    public String getLyrics(String songName)
    {

        SQLiteDatabase db2 = this.getWritableDatabase();
        String string ="";

        String selectQuery = "SELECT " + LYRICS + " FROM " + TABLE_NAME + " WHERE " + SONG_NAME + " = \"" +songName +"\"";
        Cursor cursor = db2.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                string=cursor.getString(0);

            } while (cursor.moveToNext());
        }

        return string;

    }




}



