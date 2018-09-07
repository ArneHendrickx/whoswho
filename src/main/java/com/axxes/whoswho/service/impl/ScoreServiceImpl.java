package com.axxes.whoswho.service.impl;

import com.axxes.whoswho.model.Game;
import com.axxes.whoswho.model.Person;
import com.axxes.whoswho.model.Score;
import com.axxes.whoswho.repository.GameRepository;
import com.axxes.whoswho.service.ScoreService;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        List<Game> gamesInCurrentMonth = gameRepository.findByEndTimeBetween(getFirstDayOfMonth(), getLastDayOfMonth());
        List<Score> scoreBoardWithAllScores = gamesInCurrentMonth
                .stream()
                .map(g -> new Score(
                        g.getPerson().getGivenName(),
                        g.getPerson().getSurName(),
                        g.getScore(),
                        Duration.between(g.getEndTime(), g.getEndTime()).toMillis(),
                        getAmountPlayedPerPlayer(gamesInCurrentMonth,g.getPerson())))
                .collect(Collectors.toList());

        //ToDO: Filter list, so it only contains a user once



        return null;
    }

    private LocalDate getLastDayOfMonth() {
        return LocalDate.ofEpochDay(System.currentTimeMillis() / (24 * 60 * 60 * 1000) ).plusMonths(1).withDayOfMonth(1).minusDays(1);
    }

    private LocalDate getFirstDayOfMonth() {
        return LocalDate.ofEpochDay(System.currentTimeMillis() / (24 * 60 * 60 * 1000) ).withDayOfMonth(1);
    }

    private int getAmountPlayedPerPlayer(List<Game> games, Person player) {
        return (int)games.stream()
                .filter(distinctByKey(Game::getPerson))
                .count();
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}
