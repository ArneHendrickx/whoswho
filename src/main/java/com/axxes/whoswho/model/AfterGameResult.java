package com.axxes.whoswho.model;

public class AfterGameResult {
    private int rank;
    private Score previousBestScore;
    private Score currentScore;

    public AfterGameResult() {
    }

    public AfterGameResult(int rank, Score previousBestScore, Score currentScore) {
        this.rank = rank;
        this.previousBestScore = previousBestScore;
        this.currentScore = currentScore;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Score getPreviousBestScore() {
        return previousBestScore;
    }

    public void setPreviousBestScore(Score previousBestScore) {
        this.previousBestScore = previousBestScore;
    }

    public Score getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(Score currentScore) {
        this.currentScore = currentScore;
    }
}
