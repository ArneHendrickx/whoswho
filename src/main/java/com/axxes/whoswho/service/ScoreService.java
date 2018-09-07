package com.axxes.whoswho.service;

import com.axxes.whoswho.model.Score;

import java.util.List;

public interface ScoreService {
    List<Score> generateScoreBoardAllTime();
    List<Score> generateScoreBoardMonthly();
}
