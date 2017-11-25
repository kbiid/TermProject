package com.example.kbiid.termproject;

import java.io.Serializable;

/**
 * Created by kbiid on 2017-11-18.
 */

@SuppressWarnings("serial")
public class Game implements Serializable{

    private String Songname;
    private int score;
    private int SongAddress;

    public Game(){
        Songname = "Mighty Love - JoaKim Karud";
        score = 0;
        SongAddress = R.raw.mightylove_joakimkarud;
    }

    public void setSongname(String Songname){
        this.Songname = Songname;
    }
    public void setScore(int Score){
        this.score = Score;
    }
    public void setSongAddress(int SongAddress){
        this.SongAddress = SongAddress;
    }

    public String getSongname(){
        return Songname;
    }

    public int getScore(){
        return score;
    }

    public int getSongAddress(){
        return SongAddress;
    }
}
