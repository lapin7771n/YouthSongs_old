package com.example.nikit.youthsongs.Bussiness;

import android.util.Log;

import java.util.ArrayList;

public class SongParser {

    private static String[] mTextArray;

    /********************************************
     *
     * Method for parsing chorus
     * @param textOfSong String res
     * @return ArrayList of lines that contains "|"
     *
     *********************************************/
    public static ArrayList<ArrayList<String>> parseSong(String textOfSong) {

        boolean chorusStarts = false;
        boolean verseStarts = false;
        ArrayList<ArrayList<String>> parsedSong = new ArrayList<>();

        mTextArray = textOfSong.split("\n");


        ArrayList<String> chorus = new ArrayList<>();
        ArrayList<String> verses = new ArrayList<>();

        for (String aMTextArray : mTextArray) {
            String currentLine = aMTextArray.trim().replaceAll("\t", "");
            if (currentLine.startsWith("::")) {
                chorusStarts = true;
                currentLine = currentLine.replace("::", "");
                chorus.add(currentLine);
            } else if (currentLine.endsWith("::")) {
                chorusStarts = false;
                currentLine = currentLine.replace("::", "");
                chorus.add(currentLine);
            } else if (chorusStarts) {
                chorus.add(currentLine);
            } else if (currentLine.equals("---") && !verseStarts) {
                verseStarts = true;
            } else if (currentLine.startsWith("---") && !verseStarts) {
                currentLine = currentLine.replace("---", "");
                verses.add(currentLine);
            } else if (currentLine.equals("---") && verseStarts) {
                verseStarts = false;
                verses.add("\n");
                verses.add("Chorus here");
            } else if (currentLine.endsWith("---") && verseStarts) {
                Log.e("1488", currentLine);
                currentLine = currentLine.replace("---", "");
                verses.add(currentLine);
                verses.add("\n");
                verses.add("Chorus here");
            } else {
                verses.add(currentLine);
            }
        }
        parsedSong.add(chorus);
        parsedSong.add(verses);

        return parsedSong;
    }

    private static ArrayList<String> parseChorus() {
        boolean chorusStarts = false;

        ArrayList<String> chorus = new ArrayList<>();
        for (String aMTextArray : mTextArray) {
            String currentLine = aMTextArray.trim().replaceAll("\t", "");
            if (currentLine.startsWith("::")) {
                chorusStarts = true;
                currentLine = currentLine.replace("::", "");
                chorus.add(currentLine);
            } else if (currentLine.endsWith("::")) {
                chorusStarts = false;
                currentLine = currentLine.replace("::", "");
                chorus.add(currentLine);
            } else if (chorusStarts) {
                chorus.add(currentLine);
            }
        }
        return chorus;
    }

    private static ArrayList<ArrayList<String>> parseVerses() {
        int verse = 1;

        ArrayList<ArrayList<String>> verses = new ArrayList<>();
        ArrayList<String> verseFirst = new ArrayList<>();
        ArrayList<String> verseSecond = new ArrayList<>();
        ArrayList<String> verseThird = new ArrayList<>();
        ArrayList<String> verseFourth = new ArrayList<>();
        ArrayList<String> verseFifth = new ArrayList<>();
        ArrayList<String> verseSixth = new ArrayList<>();
        ArrayList<String> verseSeventh = new ArrayList<>();

        for (int i = 0; i < mTextArray.length; i++) {
            String currentLine = mTextArray[i].trim();
            switch (verse) {
                case 1:
                    if (!currentLine.equals("")) {
                        verseFirst.add(currentLine);
                        Log.e("1488", 1 + "  :   " + currentLine);
                        mTextArray[i] = "";
                    } else {
                        verse++;
                    }
                    break;
                case 2:
                    if (!currentLine.equals("")) {
                        verseSecond.add(currentLine);
                        mTextArray[i] = "";
                        Log.e("1488", 2 + "  :   " + currentLine);
                    } else {
                        verse++;
                    }
                    break;
                case 3:
                    if (!currentLine.equals("")) {
                        verseThird.add(currentLine);
                        mTextArray[i] = "";
                    } else {
                        verse++;
                    }
                    break;
                case 4:
                    if (!currentLine.equals("")) {
                        verseFourth.add(currentLine);
                        mTextArray[i] = "";
                    } else {
                        verse++;
                    }
                    break;
                case 5:
                    if (!currentLine.equals("")) {
                        verseFifth.add(currentLine);
                        mTextArray[i] = "";
                    } else {
                        verse++;
                    }
                    break;
                case 6:
                    if (!currentLine.equals("")) {
                        verseSixth.add(currentLine);
                        mTextArray[i] = "";
                    } else {
                        verse++;
                    }
                    break;
                case 7:
                    if (!currentLine.equals("")) {
                        verseSeventh.add(currentLine);
                        mTextArray[i] = "";
                    } else {
                        verse++;
                    }
                    break;
                default:
                    break;
            }

        }

        verses.add(verseFirst);
        verses.add(verseSecond);
        verses.add(verseThird);
        verses.add(verseFourth);
        verses.add(verseFifth);
        verses.add(verseSixth);
        verses.add(verseSeventh);

        return verses;
    }
}
