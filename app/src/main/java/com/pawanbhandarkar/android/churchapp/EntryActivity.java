package com.pawanbhandarkar.android.churchapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EntryActivity extends AppCompatActivity {

    String message;
    private EGHelper db;
    String temp;
    private static final String OPEN = "Opening";
    private static final String BIBLE = "Bible";
    private static final String THANKS = "ThanksGiving";
    private static final String OFFER = "Offertory";
    private static final String CONFESS = "Confession";
    private static final String HC1TAG = "HC1";
    private static final String HC2TAG = "HC2";
    private static final String HC3TAG = "HC3";
    private static final String DOXOLOGY = "Doxology";






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new EGHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        final EditText opening = (EditText)findViewById(R.id.EntryOpening);
        opening.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                temp = db.generateRandom(OPEN);
                opening.setText(temp);
                return false;
            }
        });
        final EditText bibleReading = (EditText)findViewById(R.id.EntryBible);
        bibleReading.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                temp = db.generateRandom(BIBLE);
                bibleReading.setText(temp);
                return false;
            }
        });
        final EditText thanksGiving = (EditText)findViewById(R.id.EntryThanksGiving);
        thanksGiving.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                temp = db.generateRandom(THANKS);
                thanksGiving.setText(temp);
                return false;
            }
        });
        final EditText offertory = (EditText)findViewById(R.id.EntryOffertory);
        offertory.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                temp = db.generateRandom(OFFER);
                offertory.setText(temp);
                return false;
            }
        });
        final EditText confession = (EditText)findViewById(R.id.EntryConfession);
        confession.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                temp = db.generateRandom(CONFESS);
                confession.setText(temp);
                return false;
            }
        });
        final EditText HC1 = (EditText)findViewById(R.id.EntryHC1);
        HC1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                temp = db.generateRandom(HC1TAG);
                HC1.setText(temp);
                return false;
            }
        });
        final EditText HC2 = (EditText)findViewById(R.id.EntryHC2);
        HC2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                temp = db.generateRandom(HC2TAG);
                HC2.setText(temp);
                return false;
            }
        });
        final EditText HC3 = (EditText)findViewById(R.id.EntryHC3);
        HC3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                temp = db.generateRandom(HC3TAG);
                HC3.setText(temp);
                return false;
            }
        });
        final EditText Doxology = (EditText)findViewById(R.id.EntryDoxology);
        Doxology.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                temp = db.generateRandom(DOXOLOGY);
                Doxology.setText(temp);
                return false;
            }
        });
        final Button AddEntry = (Button)findViewById(R.id.EntryAddEntry);

        AddEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String open = opening.getText().toString();
                final String bible= bibleReading.getText().toString();
                final String thanks=thanksGiving.getText().toString();
                final String offer = offertory.getText().toString();
                final String confess = confession.getText().toString();
                final String hc1 = HC1.getText().toString();
                final String hc2 = HC2.getText().toString();
                final String hc3 = HC3.getText().toString();
                final String dox = Doxology.getText().toString();

                AlertDialog.Builder a_builder = new AlertDialog.Builder(EntryActivity.this);
                a_builder.setMessage("Are you sure you want to finalise?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                db.addData(EntryActivity.this,open,OPEN);
                                db.addData(EntryActivity.this,bible,BIBLE);
                                db.addData(EntryActivity.this,thanks,THANKS);
                                db.addData(EntryActivity.this,offer,OFFER);
                                db.addData(EntryActivity.this,confess,CONFESS);
                                db.addData(EntryActivity.this,hc1,HC1TAG);
                                db.addData(EntryActivity.this,hc2,HC2TAG);
                                db.addData(EntryActivity.this,hc3,HC3TAG);
                                db.addData(EntryActivity.this,dox,DOXOLOGY);
                                message = "Opening :" + open + "\nBible Reading : "
                                        + bible + "\nThanksgiving : " + thanks + "\nOffertory : "
                                        + offer + "\nConfession: " + confess
                                        + "\nHC1: " + hc1 + "\nHC2 :"+ hc2 + "\nHC3 :"
                                        + hc3 + "\nDoxology :" +dox;

                                opening.setText("");
                                bibleReading.setText("");
                                thanksGiving.setText("");
                                offertory.setText("");
                                confession.setText("");
                                HC1.setText("");
                                HC2.setText("");
                                HC3.setText("");
                                Doxology.setText("");

                                Intent intent  = new Intent(EntryActivity.this, EntryPopup.class);
                                intent.putExtra("EXTRA_SESSION_ID", message);
                                startActivity(intent);

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
        });





    }
}
