package com.example.memory_game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.time.Duration;
import java.time.Instant;

public class HighscoreActivity extends AppCompatActivity {

    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
    }

    public void writeTime(View v){
        Handler handler = new Handler();
        handler.postDelayed(() -> test(),1000);
        TextView t = findViewById(R.id.textView9);
        t.setText(String.valueOf(i));
    }

    public void test(){
        i++;

    }


}