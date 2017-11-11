package com.example.kbiid.termproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class GamePlayActivity extends AppCompatActivity {

    private TextView score;
    private TextView combo;
    private NoteSurfaceView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);
        //setContentView(new NoteSurfaceView(this));

        score = (TextView)findViewById(R.id.scoreView);
        combo = (TextView)findViewById(R.id.comboView);
        score.setText("Score : ");
        combo.setText("Combo : ");
        view = (NoteSurfaceView)findViewById(R.id.noteView);
    }
}
