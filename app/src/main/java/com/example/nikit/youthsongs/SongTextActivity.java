package com.example.nikit.youthsongs;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class SongTextActivity extends AppCompatActivity {

    private TextView mTextOfSong;

    private SQLiteDatabase mDb;
    private SongsDBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_text);

        Intent takenIntent = getIntent();
        String numberOfSong = takenIntent.getStringExtra("position");

        //mNumberOfSong = findViewById(R.id.text_view_number_of_song);
        //mNumberOfSong.setText(numberOfSong);

        mTextOfSong = findViewById(R.id.text_view_text_of_song);
        mTextOfSong.setVisibility(View.INVISIBLE);

        mDBHelper = new SongsDBHelper(this);

        mDBHelper.updateDataBase();

        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }

        Cursor cursor = mDb.rawQuery("SELECT * FROM Songs WHERE Number = ?", new String[] {numberOfSong});
        cursor.moveToFirst();
        mTextOfSong.setText(cursor.getString(2));
        mTextOfSong.setVisibility(View.VISIBLE);
        cursor.close();
    }
}
