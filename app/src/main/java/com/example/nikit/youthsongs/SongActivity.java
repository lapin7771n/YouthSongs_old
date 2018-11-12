package com.example.nikit.youthsongs;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.nikit.youthsongs.Bussiness.SongParser;

import java.util.ArrayList;

public class SongActivity extends AppCompatActivity {

    private TextView mTextOfSongTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_text);

        Intent takenIntent = getIntent();
        String numberOfSong = takenIntent.getStringExtra("position");
        Log.v("1488", numberOfSong);

        mTextOfSongTextView = findViewById(R.id.text_view_text_of_song);
        String textOfSong = "";

        for (int i = 0; i < Song.allSongs.size(); i++) {
            Song tmp = Song.allSongs.get(i);
            if ((tmp.getNumber() + "").equals(numberOfSong))
                textOfSong = tmp.getText();
        }

        mTextOfSongTextView.setText(structureSong(textOfSong));
        mTextOfSongTextView.setTypeface(null, Typeface.ITALIC);

    }

    private String structureSong(String textOfSong) {
        String textOfSongStruct = "";

        ArrayList<ArrayList<String>> song = SongParser.parseSong(textOfSong);


        ArrayList<String> chorus = song.get(0);
        ArrayList<String> verses = song.get(1);


        for (String verse : verses) {
            if (verse.equals("Chorus here")) {
                for (String chorusLine : chorus) {
                    textOfSongStruct += chorusLine;
                    textOfSongStruct += "\n";
                }
            } else {
                textOfSongStruct += verse;
            }
            textOfSongStruct += "\n";
        }

        return textOfSongStruct;
    }
}
