package com.example.nikit.youthsongs;

import java.util.ArrayList;

public class Song {
    static ArrayList<Song> allSongs = new ArrayList<>();

    private int mNumber;
    private String mNameOfSong;
    private String mText;
    private boolean mPresenceOfChords;

    Song() {
    }

    Song(int number, String nameOfSong, String text) {
        this.mNumber = number;
        this.mNameOfSong = nameOfSong;
        this.mText = text;
        this.mPresenceOfChords = false;
    }

    public Song(int number, String nameOfSong) {
        this.mNumber = number;
        this.mNameOfSong = nameOfSong;
    }

    /************************************************************
     *
     * Getters and overriding equals
     *
     **************************************************************/
    public int getNumber() {
        return mNumber;
    }

    public String getNameOfSong() {
        return mNameOfSong;
    }

    public String getText() {
        return mText;
    }

    public boolean isPresenceOfChords() {
        return mPresenceOfChords;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Song song = (Song) obj;
        return (this.mNumber == song.mNumber && this.mNameOfSong.equals(song.mNameOfSong));
    }

    /***********************************************
     *
     * Inner class for parsing songs
     *
     ***********************************************/
    class SongParser {

        /********************************************
         *
         * Method for parsing chords
         * @param textOfSong String res
         * @return ArrayList of lines that contains "|"
         *
         *********************************************/
        ArrayList<String> parsingChords(String textOfSong) {
            ArrayList<String> chords = new ArrayList<>();
            String[] lines = textOfSong.split("\n");
            for (String line : lines) {
                String currentLine = line.trim();
                if (currentLine.startsWith("|"))
                    chords.add(currentLine);
            }


            return chords;
        }
    }
}
