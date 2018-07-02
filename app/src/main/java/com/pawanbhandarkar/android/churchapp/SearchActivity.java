package com.pawanbhandarkar.android.churchapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final SongsHelper db = new SongsHelper(this);
        ArrayList<String> arrayList =  db.getSongNameList();

        final Spinner spinner = (Spinner) findViewById(R.id.SearchSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final TextView textView = (TextView)findViewById(R.id.LyricsDisplay);

        Button btn = (Button)findViewById(R.id.SearchButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(db.getLyrics(spinner.getSelectedItem().toString()).toString());
            }
        });

    }
}
