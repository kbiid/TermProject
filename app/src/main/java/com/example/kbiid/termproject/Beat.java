package com.example.kbiid.termproject;

/**
 * Created by kbiid on 2017-12-02.
 */

public class Beat {

    private int time;
    private String noteName;

    public Beat(int time,String noteName){
        super();
        this.time = time;
        this.noteName = noteName;
    }

    public int getTime(){
        return time;
    }

    public String getNoteName(){
        return noteName;
    }

    public void setTime(int time){
        this.time = time;
    }

    public void setNoteName(String noteName){
        this.noteName = noteName;
    }
}
