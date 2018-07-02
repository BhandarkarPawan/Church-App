package com.pawanbhandarkar.android.churchapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    String SONGNAME;
    String LYRICS;
    private SongsHelper db;
    boolean state;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        db = new SongsHelper(this);
        final EditText LyricsEdit = (EditText)findViewById(R.id.LyricsEditText);
        final EditText SongText = (EditText)findViewById(R.id.SongNameEditText);
        Button button = (Button)findViewById(R.id.EntryAddEntry);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SONGNAME = SongText.getText().toString();
                LYRICS = LyricsEdit.getText().toString();
                Log.v("AddActivity","Works Before Click");
                state=db.checkForErrors(AddActivity.this,SONGNAME, LYRICS);
                if(state) {
                    AlertDialog.Builder a_builder = new AlertDialog.Builder(AddActivity.this);
                    a_builder.setMessage("Are you sure you want to add this song to the database?")
                            .setCancelable(false)
                            .setPositiveButton
                                    ("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    db.addData(AddActivity.this, SONGNAME, LYRICS);
                                        SongText.setText("");
                                        LyricsEdit.setText("");

                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = a_builder.create();
                    alert.show();
                }




            }
        });



    }

}
