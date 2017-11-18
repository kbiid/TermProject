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
    SurfaceHolder holder;
    private Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.note);
    private int note_speed = 5;

    public NoteSurfaceView(Context context){
        super(context);
        holder = getHolder();
        holder.addCallback(this);
        //dropNotes();


        thread = new NoteAnimation();
    }

    public NoteSurfaceView(Context context,AttributeSet attr){
        super(context,attr);
        holder = getHolder();
        holder.addCallback(this);
        //dropNotes();


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
            while (true){
                Canvas canvas = holder.lockCanvas(null);

                canvas.drawColor(Color.WHITE);
                synchronized (holder){
                    for(int idx=0; idx<noteList.size(); idx++){
                        Note note = noteList.get(idx);
                        note.setBitmap(bitmap);
                        note.screenDraw(canvas);
                        //note.drop((int)getY());

                        note.screenDraw(canvas);
                    }
                }
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }

    public void dropNotes(){
        noteList.add(new Note(0,120));
        //bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.note);
        noteList.add(new Note(120,580));
        noteList.add(new Note(228,500));
        noteList.add(new Note(320,340));
        noteList.add(new Note(560,325));
        noteList.add(new Note(450,305));
        noteList.add(new Note(780,305));
    }
}
