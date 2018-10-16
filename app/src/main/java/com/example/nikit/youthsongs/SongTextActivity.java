package com.example.nikit.youthsongs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class SongTextActivity extends AppCompatActivity {

    private TextView mTextOfSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_text);

        Intent takenIntent = getIntent();
        String numberOfSong = takenIntent.getStringExtra("position");
        Log.v("1488", numberOfSong);

        mTextOfSong = findViewById(R.id.text_view_text_of_song);
        String textOfSong = "";

        for (int i = 0; i < Song.allSongs.size(); i++) {
            Song tmp = Song.allSongs.get(i);
            if ((tmp.getNumber() + "").equals(numberOfSong))
                textOfSong = tmp.getText();
        }

        mTextOfSong.setText(textOfSong);
        Song song = new Song();
        Song.SongParser songParser = song.new SongParser();
        mTextOfSong.append(songParser.parsingChords(textOfSong).toString());
    }
}
