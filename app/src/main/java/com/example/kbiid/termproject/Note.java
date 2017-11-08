package com.example.kbiid.termproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by kbiid on 2017-11-08.
 */

public class Note extends Thread{

    private Bitmap noteBasicImage = BitmapFactory.decodeFile("/res/drawable/note.png");

    private int x,y;
    private String noteType;

    public Note(int x,int y,String noteType){
        this.x = x;
        this.y = y;
        this.noteType = noteType;
    }

    public void screenDraw(Canvas g){
        if(noteType.equals("short")){
            g.drawBitmap(noteBasicImage,x,y,null);
        }
        else if(noteType.equals("long")){
            g.drawBitmap(noteBasicImage,x,y,null);
            g.drawBitmap(noteBasicImage,x+100,y,null);
        }
    }

    public void run(){

    }
}
