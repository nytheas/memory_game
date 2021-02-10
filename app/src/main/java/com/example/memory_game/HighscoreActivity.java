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
    public static final String NAME = "Name";
    private Integer high_score;
    private String my_name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        loadHighScore();
    }

    public void saveName(View v){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        TextView r = findViewById(R.id.editTextTextPersonName);
        my_name = r.getText().toString();
        editor.putString(NAME, (String) my_name);
        editor.apply();

        loadHighScore();
    }

    public void loadHighScore(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        high_score = sharedPreferences.getInt(HIGH_SCORE,0);
        my_name = sharedPreferences.getString(NAME,"Anonymous");
        TextView t = findViewById(R.id.textView7);
        t.setText("High score: "+String.valueOf(high_score));
        TextView r = findViewById(R.id.editTextTextPersonName);
        r.setText(String.valueOf(my_name));

    }


}