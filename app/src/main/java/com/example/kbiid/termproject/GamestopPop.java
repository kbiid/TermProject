package com.example.kbiid.termproject;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class GamestopPop extends Activity implements View.OnClickListener {

    private Button newGame, restart, songchoice;
    private Game gameData;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_gamestop_pop);

        newGame = (Button)findViewById(R.id.newGame);
        restart = (Button)findViewById(R.id.gameRestart);
        songchoice = (Button)findViewById(R.id.songChoice);


        newGame.setOnClickListener(this);
        restart.setOnClickListener(this);
        songchoice.setOnClickListener(this);

        intent = getIntent();
        gameData = (Game)intent.getSerializableExtra("gameData");

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()== MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }

    @Override
    public void onClick(View view) {
        if(view == newGame) {
            Intent i = new Intent(getApplicationContext(), GamePlayActivity.class);
            Bundle bundle1 = new Bundle();
            bundle1.putSerializable("gameData",gameData);
            i.putExtras(bundle1);
            i.putExtra("result", "NewGame");
            setResult(RESULT_OK, i);

            //액티비티(팝업) 닫기
            finish();
        }
        if(view == restart) {
            Intent intent = new Intent(getApplicationContext(), GamePlayActivity.class);
            intent.putExtra("result", "Re");
            setResult(RESULT_OK, intent);
            finish();
        }
        if(view == songchoice) {
            Intent intent = new Intent(getApplicationContext(), GamePlayActivity.class);
            intent.putExtra("result", "SongChoice");
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
