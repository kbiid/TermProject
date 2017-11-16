package com.example.kbiid.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
        FirebaseUser user = firebaseAuth.getCurrentUser();

        textViewUserEmail.setText("리듬게임");
        buttonStart.setOnClickListener(this);
        buttonExplain.setOnClickListener(this);
        buttonLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == buttonStart) {
            Toast.makeText(this, "게임시작", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, GamePlayActivity.class));
        }
        if(view == buttonExplain) {
            Toast.makeText(this, "게임방법", Toast.LENGTH_SHORT).show();
        }
        if(view == buttonLogout) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}