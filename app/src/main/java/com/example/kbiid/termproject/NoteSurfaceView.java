package com.example.kbiid.termproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by kbiid on 2017-11-10.
 */

public class NoteSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private NoteAnimation thread;
    private ArrayList<Note> noteList = new ArrayList<Note>();
    private SurfaceHolder holder;
    private Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.note);
    private int note_speed = 5;
    //private Canvas canvas;

    /*public NoteSurfaceView(Context context){
        super(context);
        holder = getHolder();
        holder.addCallback(this);

        //canvas = holder.lockCanvas(null);
        //canvas.drawColor(Color.WHITE);

        thread = new NoteAnimation();
    }*/

    public NoteSurfaceView(Context context,AttributeSet attr){
        super(context,attr);
        holder = getHolder();
        holder.addCallback(this);

        //canvas = holder.lockCanvas(null);
        //canvas.drawColor(Color.WHITE);

        thread = new NoteAnimation();
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder){
        dropNotes();
        thread.start();
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1,int i2){}

    public void surfaceDestroyed(SurfaceHolder surfaceHolder){
        try {
            thread.join();
        }catch (InterruptedException e){}
    }

    class NoteAnimation extends Thread{
        public void run(){
            while (true) {

                    Canvas canvas = holder.lockCanvas();
                    if(canvas == null) break;
                    canvas.drawColor(Color.WHITE);

                synchronized (holder) {
                    for (int idx = 0; idx < noteList.size(); idx++) {
                        Note note = noteList.get(idx);
                        note.setBitmap(bitmap);
                        //note.screenDraw(canvas);
                        note.drop(note_speed);
                        note.screenDraw(canvas);
                    }
                }
                    holder.unlockCanvasAndPost(canvas);
            }
        }
    }

    public void dropNotes(){
        noteList.add(new Note(0,0));
        noteList.add(new Note(192,0));
        noteList.add(new Note(384,0));
        noteList.add(new Note(576,0));
    }
}
