package com.example.newcoder.adapter;

import com.example.newcoder.model.Score;

import java.util.List;

public class ScoreAdapter {
    private List<Score> scores;
    private int score;

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
