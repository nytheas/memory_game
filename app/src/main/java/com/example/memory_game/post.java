package com.example.memory_game;

public class post {
    private int userId;

    private Integer id;

    private String title;

    private String body;

    public post(int userId, Integer id,  String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
