package com.example.memory_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.time.Duration;
import java.time.Instant;

public class HighscoreActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "SharedPrefs";
    public static final String HIGH_SCORE = "High score";
    private Integer high_score;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        loadHighScore();
    }

    public void loadHighScore(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        high_score = sharedPreferences.getInt(HIGH_SCORE,0);
        TextView t = findViewById(R.id.textView7);
        t.setText("High score: "+String.valueOf(high_score));

    }

}