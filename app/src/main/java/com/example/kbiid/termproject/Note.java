package com.example.kbiid.termproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by kbiid on 2017-11-08.
 */

public class Note{

    //private Bitmap noteBasicImage = BitmapFactory.decodeResource(getResources(), R.drawable.note);
    private Bitmap noteBasicImage;
    private int x,y;

    public Note(int x,int y){
        this.x = x;
        this.y = y;
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

}
