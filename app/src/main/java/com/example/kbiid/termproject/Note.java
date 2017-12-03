package com.example.kbiid.termproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by kbiid on 2017-11-08.
 */

public class Note{

    private Bitmap noteBasicImage;
    private int x,y;
    private String noteType;
    private int time;
    private boolean proceed = false;

    public Note(String noteType,int time){

        if(noteType.equals("A")){
            x = 0;
            y = 0;
        }
        else if(noteType.equals("B")){
            x = 270;
            y = 0;
        }
        else if(noteType.equals("C")){
            x = 540;
            y = 0;
        }
        else if(noteType.equals("D")){
            x = 810;
            y = 0;
        }
        this.time = time;
        this.noteType = noteType;
    }

    public void setBitmap(Bitmap bitmap){
        noteBasicImage = bitmap;
    }

    public void screenDraw(Canvas g){
        //if(noteBasicImage == null)
        g.drawBitmap(noteBasicImage,x,y,null);
    }

    public void drop(int y){
        this.y += y;
    }

    public int getTime(){
        return time;
    }

    public String getNoteType(){
        return noteType;
    }

    public int getY(){
        return y;
    }

    public void setProceed(){
        proceed = true;
    }

    public boolean getProceed(){
        return proceed;
    }

}
