package com.axxes.whoswho.service;

import com.axxes.whoswho.model.Game;
import com.axxes.whoswho.model.Person;
import com.axxes.whoswho.model.Score;

import java.util.List;

public interface ScoreService {
    List<Score> generateScoreBoardMonthly();
    int getAmountPlayedPerPlayer(List<Game> games, Person player);
}
