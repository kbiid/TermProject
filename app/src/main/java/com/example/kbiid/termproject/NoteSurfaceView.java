package com.example.kbiid.termproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by kbiid on 2017-11-10.
 */

public class NoteSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private NoteAnimation thread;
    private ArrayList<Note> noteList = new ArrayList<Note>();
    private SurfaceHolder holder;
    private Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.note);
    private int note_speed = 7;
    private MediaPlayer mediaPlayer;
    private String songName;
    private static final int Reach_Time = 1;
    private ImageView judgeA, judgeB,judgeC,judgeD;
    private int myscore = 0,mycombo = 0;
    private Note mynote;

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
        judgeA = (ImageView)findViewById(R.id.judgeA);
        judgeB = (ImageView)findViewById(R.id.judgeB);
        judgeC = (ImageView)findViewById(R.id.judgeC);
        judgeD = (ImageView)findViewById(R.id.judgeD);

        //canvas = holder.lockCanvas(null);
        //canvas.drawColor(Color.WHITE);

        thread = new NoteAnimation();

        /*judgeA.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                judgeline(mynote);
            }
        });
        judgeB.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                judgeline(mynote);
            }
        });
        judgeC.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                judgeline(mynote);
            }
        });
        judgeD.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                judgeline(mynote);
            }
        });*/

    }

    public void surfaceCreated(SurfaceHolder surfaceHolder){
        mediaPlayer.start();
        dropNotes();
        thread.start();
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1,int i2){}

    public void surfaceDestroyed(SurfaceHolder surfaceHolder){
        mediaPlayer.stop();
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
                        if (noteList.get(idx).getTime() <= mediaPlayer.getCurrentPosition()) {
                            final Note note = noteList.get(idx);
                            mynote = note;
                            note.setBitmap(bitmap);
                            //note.screenDraw(canvas);
                            note.drop(note_speed);
                            note.screenDraw(canvas);
                            switch (note.getNoteType()){
                                case "A" :
                                    judge(note);
                                    break;
                                case "B" :
                                    judge(note);
                                    break;
                                case "C" :
                                    judge(note);
                                    break;
                                case "D" :
                                    judge(note);
                                    break;
                            }
                        }
                    }
                }
                    holder.unlockCanvasAndPost(canvas);
            }
        }
    }

    public void dropNotes(){

        if(songName.equals("Mighty Love - Joakim Karud")){
            int startTime = 1000 - (Reach_Time * 1000);
            int gap = 125;

            noteList.add(new Note("A",startTime + gap * 2));
            noteList.add(new Note("B",startTime + gap * 4));
            noteList.add(new Note("A",startTime + gap * 2));
            noteList.add(new Note("B",startTime + gap * 4));
            noteList.add(new Note("A",startTime + gap * 6));
            noteList.add(new Note("B",startTime + gap * 8));
            noteList.add(new Note("A",startTime + gap * 10));
            noteList.add(new Note("C",startTime + gap * 12));
            noteList.add(new Note("D",startTime + gap * 14));
            noteList.add(new Note("C",startTime + gap * 16));
            noteList.add(new Note("D",startTime + gap * 18));
            noteList.add(new Note("C",startTime + gap * 20));
            noteList.add(new Note("D",startTime + gap * 22));
            noteList.add(new Note("C",startTime + gap * 24));
            noteList.add(new Note("A",startTime + gap * 26));
            noteList.add(new Note("B",startTime + gap * 28));
            noteList.add(new Note("A",startTime + gap * 30));
            noteList.add(new Note("B",startTime + gap * 32));
            noteList.add(new Note("A",startTime + gap * 36));
            noteList.add(new Note("B",startTime + gap * 38));
            noteList.add(new Note("C",startTime + gap * 40));
            noteList.add(new Note("D",startTime + gap * 42));
            noteList.add(new Note("C",startTime + gap * 44));
            noteList.add(new Note("A",startTime + gap * 46));
            noteList.add(new Note("B",startTime + gap * 48));
            noteList.add(new Note("C",startTime + gap * 49));
            noteList.add(new Note("D",startTime + gap * 50));
            noteList.add(new Note("A",startTime + gap * 52));
            noteList.add(new Note("B",startTime + gap * 52));
            noteList.add(new Note("C",startTime + gap * 52));
        }
        else if(songName.equals("Dreams - JoaKim Karud")){
            int startTime = 1000 - (Reach_Time * 1000);
            int gap = 125;

            noteList.add(new Note("A",startTime + gap * 2));
            noteList.add(new Note("B",startTime + gap * 4));
            noteList.add(new Note("A",startTime + gap * 2));
            noteList.add(new Note("B",startTime + gap * 4));
            noteList.add(new Note("A",startTime + gap * 6));
            noteList.add(new Note("B",startTime + gap * 8));
            noteList.add(new Note("A",startTime + gap * 10));
            noteList.add(new Note("C",startTime + gap * 12));
            noteList.add(new Note("D",startTime + gap * 14));
            noteList.add(new Note("C",startTime + gap * 16));
            noteList.add(new Note("D",startTime + gap * 18));
            noteList.add(new Note("C",startTime + gap * 20));
            noteList.add(new Note("D",startTime + gap * 22));
            noteList.add(new Note("C",startTime + gap * 24));
            noteList.add(new Note("A",startTime + gap * 26));
            noteList.add(new Note("B",startTime + gap * 28));
            noteList.add(new Note("A",startTime + gap * 30));
            noteList.add(new Note("B",startTime + gap * 32));
            noteList.add(new Note("A",startTime + gap * 36));
            noteList.add(new Note("B",startTime + gap * 38));
            noteList.add(new Note("C",startTime + gap * 40));
            noteList.add(new Note("D",startTime + gap * 42));
            noteList.add(new Note("C",startTime + gap * 44));
            noteList.add(new Note("A",startTime + gap * 46));
            noteList.add(new Note("B",startTime + gap * 48));
            noteList.add(new Note("C",startTime + gap * 49));
            noteList.add(new Note("D",startTime + gap * 50));
            noteList.add(new Note("A",startTime + gap * 52));
            noteList.add(new Note("B",startTime + gap * 52));
            noteList.add(new Note("C",startTime + gap * 52));
        }
        else if(songName.equals("Caffeine Rush - Jacob Tillberg")){
            int startTime = 1000 - (Reach_Time * 1000);
            int gap = 125;

            noteList.add(new Note("A",startTime + gap * 2));
            noteList.add(new Note("B",startTime + gap * 4));
            noteList.add(new Note("A",startTime + gap * 2));
            noteList.add(new Note("B",startTime + gap * 4));
            noteList.add(new Note("A",startTime + gap * 6));
            noteList.add(new Note("B",startTime + gap * 8));
            noteList.add(new Note("A",startTime + gap * 10));
            noteList.add(new Note("C",startTime + gap * 12));
            noteList.add(new Note("D",startTime + gap * 14));
            noteList.add(new Note("C",startTime + gap * 16));
            noteList.add(new Note("D",startTime + gap * 18));
            noteList.add(new Note("C",startTime + gap * 20));
            noteList.add(new Note("D",startTime + gap * 22));
            noteList.add(new Note("C",startTime + gap * 24));
            noteList.add(new Note("A",startTime + gap * 26));
            noteList.add(new Note("B",startTime + gap * 28));
            noteList.add(new Note("A",startTime + gap * 30));
            noteList.add(new Note("B",startTime + gap * 32));
            noteList.add(new Note("A",startTime + gap * 36));
            noteList.add(new Note("B",startTime + gap * 38));
            noteList.add(new Note("C",startTime + gap * 40));
            noteList.add(new Note("D",startTime + gap * 42));
            noteList.add(new Note("C",startTime + gap * 44));
            noteList.add(new Note("A",startTime + gap * 46));
            noteList.add(new Note("B",startTime + gap * 48));
            noteList.add(new Note("C",startTime + gap * 49));
            noteList.add(new Note("D",startTime + gap * 50));
            noteList.add(new Note("A",startTime + gap * 52));
            noteList.add(new Note("B",startTime + gap * 52));
            noteList.add(new Note("C",startTime + gap * 52));
        }

    }

    public void setMediaPlayer(MediaPlayer mediaPlayer,String songName){
        this.mediaPlayer = mediaPlayer;
        this.songName = songName;
    }

    public void judgeline(Note note){
        switch (note.getNoteType()){
            case "A" :
                judge(note);
                break;
            case "B" :
                judge(note);
                break;
            case "C" :
                judge(note);
                break;
            case "D" :
                judge(note);
                break;
        }
    }

    public void judge(Note note){
        if(note.getY() < 1200 || note.getY() > 1320) {
            myscore -= 100;
            if (myscore <= 0) myscore = 0;
        }
        else if(note.getY() >= 1200 || note.getY() <= 1280){
            mycombo ++;
            myscore += 80;
        }
        else if(note.getY() >= 1220 ||  note.getY() <= 1260){
            mycombo ++;
            myscore += 150;
        }
    }

    public int getMyscore(){
        return myscore;
    }

    public int getMycombo(){
        return mycombo;
    }
}
