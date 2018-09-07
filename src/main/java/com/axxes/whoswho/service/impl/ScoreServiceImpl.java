package com.axxes.whoswho.service.impl;

import com.axxes.whoswho.model.Score;
import com.axxes.whoswho.repository.GameRepository;
import com.axxes.whoswho.service.ScoreService;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    private final GameRepository gameRepository;

    public ScoreServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    //ToDo
    @Override
    public List<Score> generateScoreBoardAllTime() {
        throw new NotImplementedException();
    }

    @Override
    public List<Score> generateScoreBoardMonthly() {
        throw new NotImplementedException();
    }
}
