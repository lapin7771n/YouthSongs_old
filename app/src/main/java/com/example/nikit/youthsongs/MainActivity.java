package com.example.nikit.youthsongs;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mDBHelper = new SongsDBHelper(this);

        mlistView = findViewById(R.id.list_view_of_songs);

        mDBHelper.updateDataBase();

        mDb = mDBHelper.getWritableDatabase();
        loadDatafromDB();

        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, SongTextActivity.class);
                intent.putExtra("position", ++position + "");
                startActivity(intent);
            }
        });
    }

    //Переменная для работы с БД
    private SongsDBHelper mDBHelper;
    private SQLiteDatabase mDb;

    //ListView of songs
    private ListView mlistView;

    void loadDatafromDB() {
        ArrayList<HashMap<String, Object>> songs = new ArrayList<>();

        HashMap<String, Object> currentSong;

        Cursor cursor = mDb.rawQuery("SELECT * FROM Songs", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            //Remember ID and Name of song
            int songId = cursor.getInt(0);
            String songName = cursor.getString(1);

            currentSong = new HashMap<>();

            currentSong.put("number", songId);
            currentSong.put("name", songName);

            songs.add(currentSong);

            //Next song iteration
            cursor.moveToNext();
        }
        cursor.close();
        //Log.v("1488", songs.toString());

        String[] from = {"number", "name"};
        int[] to = {R.id.item_number, R.id.item_name};

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, songs, R.layout.adapter_song_item, from, to);
        mlistView.setAdapter(simpleAdapter);
    }


}