package com.example.memory_game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SettingsActivity extends AppCompatActivity {
    private TextView textViewResult;
    private JsonPlaceholderApi jsonPlaceholderApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        textViewResult = findViewById(R.id.textView6);

        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("http://jsonplaceholder.typicode.com/")
                .baseUrl("http://10.0.2.2:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceholderApi = retrofit.create(JsonPlaceholderApi.class);
        //getPost();
        //createPost();

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
                textViewResult.setText("");
                List<post> posts = response.body();
                int i;
                i = 1;
                for (post post : posts) {
                    String content = "";
                    content += "poradi: " + i +".        ";
                    content += "name: " + post.getName() + "        ";
                    content += "score: " + post.getScore() + "\n";

                    textViewResult.append(content);
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
}

