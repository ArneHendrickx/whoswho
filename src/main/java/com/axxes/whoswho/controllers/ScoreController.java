package com.axxes.whoswho.controllers;

import com.axxes.whoswho.model.Score;
import com.axxes.whoswho.service.ScoreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/scores")
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping("/all")
    public List<Score> generateScoreBoardAllTime() {
        return scoreService.generateScoreBoardAllTime();
    }

    @GetMapping("/monthly")
    public List<Score> getMonthlyScoreboard() {
        return scoreService.generateScoreBoardMonthly();
    }


}
