package com.example.memory_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SettingsActivity extends AppCompatActivity {
    private TextView textViewResult;
    private TextView textViewResultName;
    private TextView textViewResultScore;
    private JsonPlaceholderApi jsonPlaceholderApi;

    public static final String SHARED_PREFS = "SharedPrefs";
    public static final String HIGH_SCORE = "High score";
    public static final String NAME = "Name";
    public static final String SCORE_1 = "First score";
    public static final String SCORE_2 = "Second score";
    public static final String SCORE_3 = "Third score";
    public static final String NAME_1 = "First name";
    public static final String NAME_2 = "Second name";
    public static final String NAME_3 = "Third name";

    private Integer score1;
    private Integer score2;
    private Integer score3;
    private String name1;
    private String name2;
    private String name3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        textViewResult = findViewById(R.id.textView6);
        textViewResultName = findViewById(R.id.textView7);
        textViewResultScore = findViewById(R.id.textView9);
        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("http://jsonplaceholder.typicode.com/")
                .baseUrl("http://10.0.2.2:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceholderApi = retrofit.create(JsonPlaceholderApi.class);

        //getPost();
        //createPost();
        getLocalHighscore();

    }

    private void createPost(){
        post post = new post("myself",6);


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
                content += "SENT:";
                content += "ID:" + posts.getName() + "        ";
                content += "User ID: " + posts.getScore() + "\n";

                textViewResult.append(content);


            }

            @Override
            public void onFailure(Call<post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

    }

    private void getPost(){
        Call<List<post>> call = jsonPlaceholderApi.getPosts();
        call.enqueue(new Callback<List<post>>() {
            @Override
            public void onResponse(Call<List<post>> call, Response<List<post>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText(response.code());
                    return;

                }
                textViewResult.setText("poradi\n");
                textViewResultName.setText("jmeno\n");
                textViewResultScore.setText("score\n");
                List<post> posts = response.body();
                int i;
                i = 1;

                for (post post : posts) {
                    String content = "";
                    String contentName = "";
                    String contentScore = "";

                    content += i + ".\n";
                    contentName += post.getName() + "\n";
                    contentScore += post.getScore() + "\n";

                    textViewResult.append(content);
                    textViewResultName.append(contentName);
                    textViewResultScore.append(contentScore);
                    i++;
                }


            }

            @Override
            public void onFailure(Call<List<post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    public void getGlobal(View v){
        getPost();

    }

    public void sendGlobal(View v){
        createPost();

    }

    public void getLocalHighscore(){
        textViewResult.setText("poradi\n");
        textViewResultName.setText("jmeno\n");
        textViewResultScore.setText("score\n");

        load_values();

        textViewResult.append("1.\n2.\n3.");
        textViewResultName.append(name1 + "\n" + name2 + "\n" + name3);
        textViewResultScore.append(score1 + "\n" + score2 + "\n" + score3);


    }

        public void showLocalHighscore(View v){
            getLocalHighscore();

        }

        public void showGlobalHighscore(View v){
            getPost();

        }

    public void load_values(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);

        score1 = sharedPreferences.getInt(SCORE_1,5);
        score2 = sharedPreferences.getInt(SCORE_2,4);
        score3 = sharedPreferences.getInt(SCORE_3,3);
        name1 = sharedPreferences.getString(NAME_1,"Karel");
        name2 = sharedPreferences.getString(NAME_2,"Petr");
        name3 = sharedPreferences.getString(NAME_3,"Milan");
    }

}




