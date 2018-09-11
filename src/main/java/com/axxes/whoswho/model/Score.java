package com.axxes.whoswho.model;

public class Score {
    private String personId;
    private String firstName;
    private String lastName;
    private int score;
    private double playTimeInMillis;
    private int amountPlayed;

    public Score() {
    }

    public Score(String personId, String firstName, String lastName, int score, double playTimeInMillis, int amountPlayed) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.score = score;
        this.playTimeInMillis = playTimeInMillis;
        this.amountPlayed = amountPlayed;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getPlayTimeInMillis() {
        return playTimeInMillis;
    }

    public void setPlayTimeInMillis(double playTimeInMillis) {
        this.playTimeInMillis = playTimeInMillis;
    }

    public int getAmountPlayed() {
        return amountPlayed;
    }

    public void setAmountPlayed(int amountPlayed) {
        this.amountPlayed = amountPlayed;
    }


}
