package com.example.nikit.youthsongs.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nikit.youthsongs.R;
import com.example.nikit.youthsongs.Song;

import java.util.ArrayList;
import java.util.List;

public class SongListAdapter extends ArrayAdapter<Song> {

    private Context mContext;
    private List<Song> songList;

    public SongListAdapter(@NonNull Context context, ArrayList<Song> songs) {
        super(context, 0, songs);
        mContext = context;
        songList = songs;
    }

    @SuppressLint("DefaultLocale")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.adapter_song_item, parent, false);

        Song currentSong = songList.get(position);

        TextView itemNumber = listItem.findViewById(R.id.item_number);
        itemNumber.setText(String.format("%d", currentSong.getNumber()));

        TextView itemName = listItem.findViewById(R.id.item_name);
        itemName.setText(currentSong.getNameOfSong());

        return listItem;
    }
}
