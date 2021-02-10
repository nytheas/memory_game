package com.example.memory_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void switch_to_about(View v) {
        Intent i = new Intent(this, AboutActivity.class);
        startActivity(i);
    }
    public void switch_to_settings(View v) {
        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
    }
    public void switch_to_highscore(View v) {
        Intent i = new Intent(this, HighscoreActivity.class);
        startActivity(i);
    }
    public void switch_to_game(View v) {
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);
    }



}