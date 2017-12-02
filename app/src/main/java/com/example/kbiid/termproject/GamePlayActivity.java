package com.example.kbiid.termproject;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GamePlayActivity extends AppCompatActivity {

    private TextView score;
    private TextView combo;
    private NoteSurfaceView view;
    private Game gameData;
    private Intent intent;
    private Button btn;
    private Music music;
    private ImageView judgeA, judgeB,judgeC,judgeD;

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
        score = (TextView)findViewById(R.id.scoreView);
        combo = (TextView)findViewById(R.id.comboView);
        score.setText("Score : ");
        combo.setText("Combo : ");
        judgeA.setAlpha(0.5f);
        judgeB.setAlpha(0.5f);
        judgeC.setAlpha(0.5f);
        judgeD.setAlpha(0.5f);
        view = (NoteSurfaceView)findViewById(R.id.noteView);

        view.setVisibility(View.INVISIBLE);
        intent = getIntent();
        gameData = (Game)intent.getSerializableExtra("gameData");
        music = new Music(this,gameData.getSongAddress());
        view.setMediaPlayer(music.getMediaPlayer(),gameData.getSongname());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn.setVisibility(View.GONE);
                view.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        //music.musicStop();
    }
}
