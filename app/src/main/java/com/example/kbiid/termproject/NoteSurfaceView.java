package com.example.kbiid.termproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kbiid on 2017-11-10.
 */

public class NoteSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private static final int noteMsg = 1, noteMsg2 = 2;
    private NoteAnimation thread;
    private ArrayList<Note> noteList = new ArrayList<Note>();
    private SurfaceHolder holder;
    private Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.note);
    private int note_speed = 7;
    private MediaPlayer mediaPlayer;
    private String songName;
    private static final int Reach_Time = 1;
    public static List<Note> mynoteList = new ArrayList<Note>();

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
                            Note note = noteList.get(idx);
                            note.setBitmap(bitmap);
                            //note.screenDraw(canvas);
                            note.drop(note_speed);
                            note.screenDraw(canvas);
                            if((note.getY() > 2400) && (note.getProceed() == false)) {
                                Message msg = GamePlayActivity.mHandler.obtainMessage(noteMsg2, note);
                                GamePlayActivity.mHandler.sendMessage(msg);
                                note.setProceed();
                                Log.d("Note : ","1");
                                //noteList.remove(idx);
                            }
                            else if(note.getProceed()){
                                Log.d("Note : ","2");
                                noteList.remove(idx);
                            }
                            else {
                                Message msg = GamePlayActivity.mHandler.obtainMessage(noteMsg, note);
                                GamePlayActivity.mHandler.sendMessage(msg);
                                //noteList.remove(idx);
                            }
                        }
                    }
                }
                    holder.unlockCanvasAndPost(canvas);
            }
        }
    }

    public void dropNotes(){

        if(songName.equals("a road - DIA")){
            int startTime = 1000 - (Reach_Time * 1000);
            int gap = 125;

            /*
            noteList.add(new Note("D",startTime + gap * 18));
            noteList.add(new Note("A",startTime + gap * 20));
            noteList.add(new Note("B",startTime + gap * 22));
            noteList.add(new Note("C",startTime + gap * 24));
            noteList.add(new Note("D",startTime + gap * 26));
            noteList.add(new Note("A",startTime + gap * 28));
            noteList.add(new Note("B",startTime + gap * 30));
            noteList.add(new Note("C",startTime + gap * 32));

            noteList.add(new Note("B",startTime + gap * 27));
            noteList.add(new Note("C",startTime + gap * 34));
            noteList.add(new Note("D",startTime + gap * 41));
            noteList.add(new Note("A",startTime + gap * 48));
            noteList.add(new Note("B",startTime + gap * 55));
            noteList.add(new Note("C",startTime + gap * 62));
            noteList.add(new Note("D",startTime + gap * 69));
            noteList.add(new Note("A",startTime + gap * 76));
            noteList.add(new Note("B",startTime + gap * 83));
            noteList.add(new Note("C",startTime + gap * 90));
            noteList.add(new Note("D",startTime + gap * 97));
            noteList.add(new Note("A",startTime + gap * 104));
            noteList.add(new Note("B",startTime + gap * 111));
            noteList.add(new Note("C",startTime + gap * 118));
            noteList.add(new Note("D",startTime + gap * 125));
            noteList.add(new Note("A",startTime + gap * 132));
            noteList.add(new Note("B",startTime + gap * 139));
            noteList.add(new Note("C",startTime + gap * 146));
            noteList.add(new Note("D",startTime + gap * 153));
            noteList.add(new Note("A",startTime + gap * 160));
            noteList.add(new Note("B",startTime + gap * 167));
            */
        }
        else if(songName.equals("Dreams - JoaKim Karud")){
            int startTime = 1000 - (Reach_Time * 1000);
            int gap = 125;

            noteList.add(new Note("A",startTime + gap * 2));
            noteList.add(new Note("B",startTime + gap * 4));
            //noteList.add(new Note("A",startTime + gap * 2));
            //noteList.add(new Note("B",startTime + gap * 4));
            //noteList.add(new Note("A",startTime + gap * 6));
            //noteList.add(new Note("B",startTime + gap * 8));
            //noteList.add(new Note("A",startTime + gap * 10));
            //noteList.add(new Note("C",startTime + gap * 12));
            //noteList.add(new Note("D",startTime + gap * 14));
            //noteList.add(new Note("C",startTime + gap * 16));
            //noteList.add(new Note("D",startTime + gap * 18));
            noteList.add(new Note("C",startTime + gap * 20));
            //noteList.add(new Note("D",startTime + gap * 22));
            //noteList.add(new Note("C",startTime + gap * 24));
           // noteList.add(new Note("A",startTime + gap * 26));
            //noteList.add(new Note("B",startTime + gap * 28));
            //noteList.add(new Note("A",startTime + gap * 30));
            //noteList.add(new Note("B",startTime + gap * 32));
            noteList.add(new Note("C",startTime + gap * 36));
            //noteList.add(new Note("B",startTime + gap * 38));
            //noteList.add(new Note("C",startTime + gap * 40));
            //noteList.add(new Note("D",startTime + gap * 42));
            //noteList.add(new Note("C",startTime + gap * 44));
            //noteList.add(new Note("A",startTime + gap * 46));
            //noteList.add(new Note("B",startTime + gap * 48));
            //noteList.add(new Note("C",startTime + gap * 49));
            //noteList.add(new Note("D",startTime + gap * 50));
            //noteList.add(new Note("A",startTime + gap * 52));
            //noteList.add(new Note("B",startTime + gap * 52));
            //noteList.add(new Note("C",startTime + gap * 52));
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
}
