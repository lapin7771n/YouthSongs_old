package com.example.nikit.youthsongs;

public class Song {

    private int number;
    private String nameOfSong;
    private String text;
    private boolean presenceOfChords;

    public Song() {
    }

    public Song(int number, String nameOfSong, String text, boolean presenceOfChords) {
        this.number = number;
        this.nameOfSong = nameOfSong;
        this.text = text;
        this.presenceOfChords = presenceOfChords;
    }

    public Song(int number, String nameOfSong) {
        this.number = number;
        this.nameOfSong = nameOfSong;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        if (number != 0) this.number = number;
    }

    public String getNameOfSong() {
        return nameOfSong;
    }

    public void setNameOfSong(String nameOfSong) {
        if (nameOfSong!=null) this.nameOfSong = nameOfSong;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isPresenceOfChords() {
        return presenceOfChords;
    }

    public void setPresenceOfChords(boolean presenceOfChords) {
        this.presenceOfChords = presenceOfChords;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Song song = (Song) obj;
        return (this.number == song.number && this.nameOfSong.equals(song.nameOfSong));
    }
}
