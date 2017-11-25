package com.example.kbiid.termproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    private Button buttonStart;
    private Button buttonExplain;
    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        textViewUserEmail = (TextView) findViewById(R.id.textviewUserEmail);
        buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonExplain = (Button) findViewById(R.id.buttonExplain);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        textViewUserEmail.setText("리듬게임");
        buttonStart.setOnClickListener(this);
        buttonExplain.setOnClickListener(this);
        buttonLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == buttonStart) {
            Toast.makeText(this, "게임시작", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, SongChoiceScreenActivity.class));
        }
        if(view == buttonExplain) {
            Context mContext = getApplicationContext();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.dialog,(ViewGroup) findViewById(R.id.manulayout));
            AlertDialog.Builder aDialog = new AlertDialog.Builder(MainActivity.this);
            aDialog.setTitle("게임방법");
            aDialog.setView(layout);
            aDialog.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog ad = aDialog.create();
            ad.show();
        }
        if(view == buttonLogout) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}