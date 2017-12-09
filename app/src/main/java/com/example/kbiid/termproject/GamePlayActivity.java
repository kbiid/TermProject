package com.example.kbiid.termproject;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GamePlayActivity extends AppCompatActivity implements View.OnClickListener {

    public static TextView score,combo;
    private NoteSurfaceView view;
    private Game gameData;
    private Intent intent;
    private Button btn, stopgameBtn;
    private Music music;
    public static ImageView judgeA, judgeB,judgeC,judgeD;
    public static Handler mHandler;
    private int myScore = 0,myCombo = 0;
    private ArrayList<Note> noteListA,noteListB,noteListC,noteListD;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_game_play);

        judgeA = (ImageView)findViewById(R.id.judgeA);
        judgeB = (ImageView)findViewById(R.id.judgeB);
        judgeC = (ImageView)findViewById(R.id.judgeC);
        judgeD = (ImageView)findViewById(R.id.judgeD);
        btn = (Button)findViewById(R.id.startButton);
        stopgameBtn = (Button)findViewById(R.id.stopgameBtn);
        score = (TextView)findViewById(R.id.scoreView);
        combo = (TextView)findViewById(R.id.comboView);
        score.setText("Score : ");
        combo.setText("Combo : ");
        judgeA.setAlpha(0.5f);
        judgeB.setAlpha(0.5f);
        judgeC.setAlpha(0.5f);
        judgeD.setAlpha(0.5f);
        view = (NoteSurfaceView)findViewById(R.id.noteView);

        noteListA = new ArrayList<Note>();
        noteListB = new ArrayList<Note>();
        noteListC = new ArrayList<Note>();
        noteListD = new ArrayList<Note>();

        GamePlayActivity.combo.setText("Combo: " + Integer.toString(myCombo));
        GamePlayActivity.score.setText("Score: " + Integer.toString(myScore));
        //view.setVisibility(View.INVISIBLE);
        btn.setVisibility(View.GONE);
        view.setVisibility(View.VISIBLE);
        intent = getIntent();
        gameData = (Game)intent.getSerializableExtra("gameData");
        music = new Music(this,gameData.getSongAddress());
        view.setMediaPlayer(music.getMediaPlayer(),gameData.getSongname());
        stopgameBtn.setOnClickListener(this);

        judgeA.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(!(noteListA.size() <= countA)) {
                    judge(noteListA.get(0));
                    noteListA.get(0).setProceed();
                    noteListA.remove(0);
                    //countA++;
                //}
            }
        });
        judgeB.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(!(noteListB.size() <= countB)) {
                    judge(noteListB.get(0));
                    noteListB.get(0).setProceed();
                    noteListB.remove(0);
                    //countB++;
                //}
                //judge(noteListB.get(0));
                //noteListB.get(0).setProceed();
                //noteListB.remove(0);
            }
        });
        judgeC.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(!(noteListC.size() <= countC)) {
                    judge(noteListC.get(0));
                    noteListC.get(0).setProceed();
                    noteListC.remove(0);
                    //countC++;
                //}
                //judge(noteListC.get(0));
                //noteListC.get(0).setProceed();
                //noteListC.remove(0);
            }
        });
        judgeD.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(!(noteListD.size() <= countD)) {
                    judge(noteListD.get(0));
                    noteListD.get(0).setProceed();
                    noteListD.remove(0);
                    //countD++;
                //}
                //judge(noteListD.get(0));
                //noteListD.get(0).setProceed();
                //noteListD.remove(0);
            }
        });

        mHandler = new Handler(){
          @Override
            public void handleMessage(Message msg){
              super.handleMessage(msg);
              if(msg.what == 1) {
                  judgeline((Note)msg.obj);
              }
              else if(msg.what == 2){
                  myCombo = 0;
                  myScore -= 100;
                  if (myScore <= 0) myScore = 0;
                  GamePlayActivity.combo.setText("Combo: " + Integer.toString(myCombo));
                  GamePlayActivity.score.setText("Score: " + Integer.toString(myScore));
              }
          }
        };
    }

    public void judgeline(Note note){
        switch (note.getNoteType()){
            case "A" :
                noteListA.add(note);
                break;
            case "B" :
                noteListB.add(note);
                break;
            case "C" :
                noteListC.add(note);
                break;
            case "D" :
                noteListD.add(note);
                break;
        }
    }

    public void judge(Note note){
        if((note.getY() > 1810) && (note.getProceed() == false)) {
            myCombo = 0;
            myScore -= 100;
            if (myScore <= 0) myScore = 0;
        }
        else if((note.getY() >= 1720 || note.getY() < 1800) && (note.getProceed() == false)) {
            if(note.getY() >= 1750 ||  note.getY() < 1810){
                myCombo ++;
                myScore += 150;
            }
            else {
                myCombo++;
                myScore += 80;
            }
        }
        GamePlayActivity.combo.setText("Combo: " + Integer.toString(myCombo));
        GamePlayActivity.score.setText("Score: " + Integer.toString(myScore));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1){
            if(resultCode==RESULT_OK){

                String result = data.getStringExtra("result");
                if( result.equals("SongChoice")) {
                    finish();
                    startActivity(new Intent(getApplicationContext(), SongChoiceScreenActivity.class));
                }else if( result.equals("Re")){

                }else if( result.equals("NewGame")){
                    finish();

                    Intent in = getIntent();
                    Game getGameData = (Game)in.getSerializableExtra("gameData");


                    Intent i = new Intent(getApplicationContext(), GamePlayActivity.class);
                    Bundle getBundle = new Bundle();
                    getBundle.putSerializable("gameData",getGameData);
                    i.putExtras(getBundle);

                    startActivity(i);
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        GamePlayActivity.super.onPause();
        startActivity(new Intent(this, GamestopPop.class));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void onClick(View view) {
        if(view == stopgameBtn) {
            GamePlayActivity.super.onPause();
            //startActivity(new Intent(this, GamestopPop.class));
            Intent intent = new Intent(getApplicationContext(), GamestopPop.class);
            bundle = new Bundle();
            bundle.putSerializable("gameData",gameData);
            intent.putExtras(bundle);
            startActivityForResult(intent, 1);
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void onPause() {
        super.onPause();
        music.musicPause();

    }

    public void onResume() {
        super.onResume();
        music.musicStart();
    }
}
