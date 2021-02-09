package com.example.memory_game;

public class post {
    private String name;

    private Integer score;



    public post(String name, Integer score ) {
        this.name = name;
        this.score = score;

    }

    public String getName() {
        return name;
    }

    public Integer getScore() {
        return score;
    }

}
