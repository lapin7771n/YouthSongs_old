package com.example.nikit.youthsongs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nikit.youthsongs.Adapters.SongListAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();


        //Переменная для работы с БД
        SongsDBHelper mDBHelper = new SongsDBHelper(this);

        mlistView = findViewById(R.id.list_view_of_songs);

        mDBHelper.updateDataBase();

        mDb = mDBHelper.getWritableDatabase();
        AsyncTaskFromDB asyncTaskFromDB = new AsyncTaskFromDB();
        asyncTaskFromDB.execute();

        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, SongActivity.class);
                @SuppressLint("ResourceType") View v = ((LinearLayout) view).getChildAt(0);
                String numberOfSong = ((TextView) v).getText().toString();
                intent.putExtra("position", numberOfSong + "");
                startActivity(intent);
            }
        });
    }

    private SQLiteDatabase mDb;

    //ListView of songs
    private ListView mlistView;


    /************************************************
     *
     * Setting all data up and filling ListView
     *
     ************************************************/
    @SuppressLint("StaticFieldLeak")
    private class AsyncTaskFromDB extends AsyncTask<Void, Void, Void> {

        private ArrayList<Song> songs = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Cursor cursor = mDb.rawQuery("SELECT * FROM Songs", null);
            cursor.moveToFirst();
            cursor.moveToNext();

            while (!cursor.isAfterLast()) {
                //Remember ID and Name of song
                int songId = cursor.getInt(0);
                String songName = cursor.getString(1);
                if (songName != null && !songName.equals(""))
                    songName = songName.trim();
                else {
                    cursor.moveToNext();
                    continue;
                }

                Song currentSong = new Song(songId, songName, cursor.getString(2));

                songs.add(currentSong);

                //Next song iteration
                cursor.moveToNext();
            }
            cursor.close();
            Log.v("1488", String.valueOf(songs.size()));

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            SongListAdapter songListAdapter = new SongListAdapter(mContext, songs);
            mlistView.setAdapter(songListAdapter);
            Song.allSongs = songs;
        }
    }

//    void loadDataFromDB() {
//        ArrayList<Song> songs = new ArrayList<>();
//
//        Cursor cursor = mDb.rawQuery("SELECT * FROM Songs", null);
//        cursor.moveToFirst();
//        cursor.moveToNext();
//
//        while (!cursor.isAfterLast()) {
//            //Remember ID and Name of song
//            int songId = cursor.getInt(0);
//            String songName = cursor.getString(1);
//            if (songName != null && !songName.equals(""))
//                songName = songName.trim();
//            else {
//                cursor.moveToNext();
//                continue;
//            }
//
//            Song currentSong = new Song(songId, songName, cursor.getString(2));
//
//            songs.add(currentSong);
//
//            //Next song iteration
//            cursor.moveToNext();
//        }
//        cursor.close();
//        Log.v("1488", String.valueOf(songs.size()));
//
//        SongListAdapter songListAdapter = new SongListAdapter(this, songs);
//        mlistView.setAdapter(songListAdapter);
//        Song.allSongs = songs;
//    }


}