package com.example.memory_game;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceholderApi {
    @GET("posts")
    Call<List<post>> getPosts();

    @POST("posts")
    Call<post> createPost(@Body post post);

}
