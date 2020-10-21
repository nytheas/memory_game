package com.example.memory_game;

import androidx.annotation.ColorRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    List pressedButtons = new ArrayList();
    List sequence = new ArrayList();

    Integer seqLenth = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    public void pressButton(View v){
        Integer s = v.getId();
        String b = ((Button)v).getText().toString();
        pressedButtons.add(b);
        TextView t = findViewById(R.id.textView8);
        t.setText(pressedButtons.toString());
        v.setBackgroundColor(getResources().getColor(R.color.teal_200));

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                v.setBackgroundColor(getResources().getColor(R.color.purple_500));}
        }, 500);

    }

    public void clearSequence(View v){
        pressedButtons.clear();
        TextView t = findViewById(R.id.textView8);
        t.setText(pressedButtons.toString());

    }

    public void flashButton(Button b){
        Handler handler = new Handler();
        handler.postDelayed(() -> flash_button(b),1000);
        handler.postDelayed(() -> flash_back(b),2000);
    }


    public void generateRandom(View v){
        Random rand = new Random();
        int num = rand.nextInt(9)+1;
        sequence.add(num);
        switch (num) {
            case 1:
                flashButton(findViewById(R.id.button11));
                break;
            case 2:
                flashButton(findViewById(R.id.button21));
                break;
            case 3:
                flashButton(findViewById(R.id.button31));
                break;
            case 4:
                flashButton(findViewById(R.id.button12));
                break;
            case 5:
                flashButton(findViewById(R.id.button22));
                break;
            case 6:
                flashButton(findViewById(R.id.button32));
                break;
            case 7:
                flashButton(findViewById(R.id.button13));
                break;
            case 8:
                flashButton(findViewById(R.id.button23));
                break;
            case 9:
                flashButton(findViewById(R.id.button33));
                break;
        }
    }

    public void generateSequenceTest(View v){
        sequence.clear();
        Handler handler = new Handler();
        for (int i = 0; i < seqLenth; i++) {
            handler.postDelayed(() -> generateRandom(v), 1000*i);
        }
        printSequence(v);
    }

    public void printSequence(View v){
        TextView t = findViewById(R.id.textView8);
        t.setText(sequence.toString());
    }

    public void generateSequence(View v){
        Random rand = new Random();
        //sequence.clear();
        for (int i = 0; i < seqLenth; i++){

            int num = rand.nextInt(9)+1;
            sequence.add(num);
             switch (num) {
                case 1:
                    flashButton(findViewById(R.id.button11));
                    break;
                case 2:
                    flashButton(findViewById(R.id.button21));
                    break;
                case 3:
                    flashButton(findViewById(R.id.button31));
                    break;
                case 4:
                    flashButton(findViewById(R.id.button12));
                    break;
                case 5:
                    flashButton(findViewById(R.id.button22));
                    break;
                case 6:
                    flashButton(findViewById(R.id.button32));
                    break;
                case 7:
                    flashButton(findViewById(R.id.button13));
                    break;
                case 8:
                    flashButton(findViewById(R.id.button23));
                    break;
                case 9:
                    flashButton(findViewById(R.id.button33));
                    break;
            }
        }

        TextView t = findViewById(R.id.textView8);
        t.setText(sequence.toString());

    }


    public void flash_button(Button b){
        b.setBackgroundColor(getResources().getColor(R.color.teal_200));
    }

    public void flash_back(Button b){
        b.setBackgroundColor(getResources().getColor(R.color.purple_500));

    }

    public void evaluate(View v){
        TextView t = findViewById(R.id.textView8);
        Boolean eq = true;

        if (sequence.equals(pressedButtons)){
            t.setText("Jipikaei");
        } else {
            t.setText("You are failure");
        }

        sequence.clear();
        pressedButtons.clear();
    }


}