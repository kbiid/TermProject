package com.example.kbiid.termproject;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class GamePlayActivity extends AppCompatActivity {

    ArrayList<Note> noteList = new ArrayList<Note>();
    Canvas g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        for(int i=0;i<noteList.size();i++){
                Note note = noteList.get(i);
                note.screenDraw(g);
        }
    }

    public void dropNotes(String titleName){
        noteList.add(new Note(228,120,"short"));
        noteList.add(new Note(332,580,"short"));
        noteList.add(new Note(436,500,"short"));
        noteList.add(new Note(540,340,"long"));
        noteList.add(new Note(744,325,"short"));
        noteList.add(new Note(848,305,"short"));
        noteList.add(new Note(952,305,"short"));
    }
}
