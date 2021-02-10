package com.example.memory_game;

import androidx.annotation.ColorRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GameActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "SharedPrefs";
    public static final String HIGH_SCORE = "High score";
    public static final String NAME = "Name";
    private String my_name;
    private Integer high_score;
    private TextView textViewResult;
    private JsonPlaceholderApi jsonPlaceholderApi;





    List pressedButtons = new ArrayList();
    List sequence = new ArrayList();

    Integer seqLength = 3;
    Integer lives = 3;
    Integer status = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        loadHighScore();
        TextView t = findViewById(R.id.textView10);
        t.setText("Sequence length:" + String.valueOf(seqLength));
        TextView t2 = findViewById(R.id.textView11);
        t2.setText("Lives remaining:" + String.valueOf(lives));
        textViewResult = findViewById(R.id.textView8);

        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("http://jsonplaceholder.typicode.com/")
                .baseUrl("http://10.0.2.2:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceholderApi = retrofit.create(JsonPlaceholderApi.class);

        }


    public void pressButton(View v){
        Integer s = v.getId();
        String b = ((Button)v).getText().toString();
        pressedButtons.add(b);
        //TextView t = findViewById(R.id.textView8);
        //t.setText(pressedButtons.toString());
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
        for (int i = 0; i < seqLength; i++) {
            handler.postDelayed(() -> generateRandom(v), 1000*i);
        }
        printSequence(v);
    }

    public void gameRound(View v){
        Button b = findViewById(R.id.button9);
        if (status == 0) {
            // generate sequence
            b.setText("Evaluate");
            generateSequenceTest(v);
            status = 1;
        }
        else if (status == 1) {
            // check sequence
            status = 0;
            evaluate(v);
            b.setText("Next round");

        }
        if (status == 2) {
            b.setText("Exit to menu");
            status = 3;
            createPost();
        }
        else if (status == 3) {
            finish();
        }
     }


    public void printSequence(View v){
        TextView t = findViewById(R.id.textView8);
        // t.setText(sequence.toString());
    }

    public void generateSequence(View v){
        Random rand = new Random();
        //sequence.clear();
        for (int i = 0; i < seqLength; i++){

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

        Boolean same = compare_array(sequence,pressedButtons);

        if (same){
            t.setText("Correct!");
            seqLength++;
        } else {
            t.setText("Wrong! Try again!");
            lives--;
        }

        if (lives == 0) {
            saveHighScore();
            t.setText("You are dead. Your score is " + String.valueOf(seqLength));
            status = 2;
 
        }
        printMenu(v);
        //Log.d("compare", "got here"+ same);
        sequence.clear();
        pressedButtons.clear();
    }

    public Boolean compare_array(List array1, List array2){
        int len1 = array1.size();
        int len2 = array2.size();


        Log.d("compare", "here1");

        if (len1 != len2){
            Log.d("compare", "here2");
            return Boolean.FALSE;
        }

        for (int i = 0; i < len1; i++){
            Log.d("compare", "here3");
            int ac1 = (int) array1.get(i);
            //int ac2 = (int) array1.get(i);
            String ac3 = (String) array2.get(i);
            int ac2 = Integer.valueOf(ac3);
            //Log.d("compare", "string?: "+ac3);
            if (ac1 != ac2) {
                Log.d("compare", "compare_FAIL: "+ ac1+"|"+ac2);
                return Boolean.FALSE;
            } else {
                Log.d("compare", "compare_SUCCESS: "+ ac1+"|"+ac2);
            }
        }
        return Boolean.TRUE;
    }


    public void printMenu(View v){
        TextView t = findViewById(R.id.textView10);
        t.setText("Sequence length:"+String.valueOf(seqLength));
        TextView t2 = findViewById(R.id.textView11);
        t2.setText("Lives remaining:"+String.valueOf(lives));

    }

    public void saveHighScore(){
        if (seqLength > high_score) {
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(HIGH_SCORE, seqLength);
            editor.apply();
        }

    }

    public void loadHighScore(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        my_name = sharedPreferences.getString(NAME,"anonymous");
        high_score = sharedPreferences.getInt(HIGH_SCORE,0);
        // TextView t = findViewById(R.id.textView12);
        // t.setText("High score: "+String.valueOf(high_score));

        }

    private void createPost(){
        post post = new post(my_name,seqLength);


        Call<post> call = jsonPlaceholderApi.createPost(post);
        call.enqueue(new Callback<post>() {
            @Override
            public void onResponse(Call<post> call, Response<post> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText(response.code());
                    return;
                }
                textViewResult.setText("");
                post posts = response.body();

                String content = "";
                content += "SENT to HIGHSCORE:";
                content += "name:" + posts.getName() + "        ";
                content += "score: " + posts.getScore() + "\n";

                textViewResult.append(content);


            }

            @Override
            public void onFailure(Call<post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

    }

}