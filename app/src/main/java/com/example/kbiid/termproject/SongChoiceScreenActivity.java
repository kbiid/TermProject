package com.example.kbiid.termproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class SongChoiceScreenActivity extends AppCompatActivity {

    private ViewFlipper flipper;
    private TextView songName;
    private TextView score;
    private  Animation showin;
    private Game gameData;
    private Intent intent;
    private Bundle bundle;

    private int sum=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_choice_screen);

        flipper=(ViewFlipper)findViewById(R.id.flipper);
        songName = (TextView)findViewById(R.id.songName);
        score = (TextView)findViewById(R.id.ScoreView);

        gameData = new Game();
        flipper.setDisplayedChild(1);
        //gameData.setSongname("Dreams - JoaKim Karud");
        //songName.setText(gameData.getSongname());

        //gameData.setSongAddress(R.raw.dreams_joakim_karud);
        //score.setText(Integer.toString(gameData.getScore()));

        showFlipper();

    }

    //버튼 클릭이벤트
    public void myOnclick(View v){
        switch (v.getId()){
            case R.id.previousButton:
                //이전버튼을 눌렀을 때 이미지가 오른쪽에서 등장
                //왼쪽에서 이미지가 퇴장
                showin = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
                flipper.setInAnimation(showin);
                flipper.setOutAnimation(this, R.anim.slide_out_left);

                flipper.showPrevious();
                showFlipper();

                break;
            case R.id.nextButton:
                //다음버튼을 눌렀을 때 이미지가 왼쪽에서 등장
                //오른쪽에서 퇴장
                showin = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
                flipper.setInAnimation(showin);
                flipper.setOutAnimation(this, android.R.anim.slide_out_right);

                flipper.showNext();
                showFlipper();

                break;
        }
    }

    //뷰플리퍼 클릭 이벤트
    public boolean onTouchEvent(MotionEvent touchevent){
        sum++;

        if( sum == 3 ){
            sum = 0;

            finish();

            intent = new Intent(SongChoiceScreenActivity.this, GamePlayActivity.class);
            bundle = new Bundle();
            bundle.putSerializable("gameData",gameData);
            intent.putExtras(bundle);

            startActivity(intent);
        }
        return false;
    }
    //뷰플리퍼 사진마다 이름과 점수 표시
    public void showFlipper(){
        switch (flipper.getDisplayedChild()){

            case 0:
                gameData.setSongname("a road - DIA");
                songName.setText(gameData.getSongname());
                songName.setTextSize(30);
                songName.setTextColor(Color.WHITE);

                gameData.setSongAddress(R.raw.a_road);
                score.setText(Integer.toString(gameData.getScore()));
                score.setTextSize(30);
                score.setTextColor(Color.WHITE);

                break;

            case 1:
                gameData.setSongname("Dreams - JoaKim Karud");
                songName.setText(gameData.getSongname());
                songName.setTextSize(30);
                songName.setTextColor(Color.WHITE);

                gameData.setSongAddress(R.raw.dreams_joakim_karud);
                score.setText(Integer.toString(gameData.getScore()));
                score.setTextSize(30);
                score.setTextColor(Color.WHITE);

                break;

            case 2:
                gameData.setSongname("Caffeine Rush - Jacob Tillberg");
                songName.setText(gameData.getSongname());
                songName.setTextSize(30);
                songName.setTextColor(Color.WHITE);

                gameData.setSongAddress(R.raw.jacob_tillberg_caffeinerush);
                score.setText(Integer.toString(gameData.getScore()));
                score.setTextSize(30);
                score.setTextColor(Color.WHITE);

                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
       }
}
