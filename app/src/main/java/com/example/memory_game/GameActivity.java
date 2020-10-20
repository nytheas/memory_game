package com.example.memory_game;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
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

    public void generateSequence(View v){
        Random rand = new Random();
        sequence.clear();
        for (int i = 0; i < seqLenth; i++){
            int num = rand.nextInt(9)+1;
            sequence.add(num);
             switch (num) {
                case 1:
                    flash_button(findViewById(R.id.button11));
                    break;
                case 2:
                    flash_button(findViewById(R.id.button21));
                    break;
                case 3:
                    flash_button(findViewById(R.id.button31));
                    break;
                case 4:
                    flash_button(findViewById(R.id.button12));
                    break;
                case 5:
                    flash_button(findViewById(R.id.button22));
                    break;
                case 6:
                    flash_button(findViewById(R.id.button32));
                    break;
                case 7:
                    flash_button(findViewById(R.id.button13));
                    break;
                case 8:
                    flash_button(findViewById(R.id.button23));
                    break;
                case 9:
                    flash_button(findViewById(R.id.button33));
                    break;
            }



        }
        TextView t = findViewById(R.id.textView8);
        t.setText(sequence.toString());

    }


    public void flash_button(Button b){
        b.setBackgroundColor(getResources().getColor(R.color.teal_200));

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                b.setBackgroundColor(getResources().getColor(R.color.purple_500));}
        }, 2000);


    }


}