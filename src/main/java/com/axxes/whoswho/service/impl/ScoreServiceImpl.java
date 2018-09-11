package com.axxes.whoswho.service.impl;

import com.axxes.whoswho.model.Game;
import com.axxes.whoswho.model.Person;
import com.axxes.whoswho.model.Score;
import com.axxes.whoswho.repository.GameRepository;
import com.axxes.whoswho.service.ScoreService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScoreServiceImpl implements ScoreService {

    private final GameRepository gameRepository;

    public ScoreServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Score> generateScoreBoardMonthly() {
        List<Game> gamesInCurrentMonth = gameRepository.findByEndTimeBetween(getFirstDayOfMonth(), getLastDayOfMonth());

        return gamesInCurrentMonth
                .stream()
                .map(game -> new Score(
                        game.getPerson().getId(),
                        game.getPerson().getGivenName(),
                        game.getPerson().getSurName(),
                        game.getScore(),
                        Duration.between(game.getEndTime(), game.getEndTime()).toMillis(),
                        getAmountPlayedPerPlayer(gamesInCurrentMonth,game.getPerson())))
                .sorted(Comparator.comparing(Score::getScore).reversed().thenComparing(Score::getPlayTimeInMillis))
                .collect(Collectors.groupingBy(Score::getPersonId)).entrySet().iterator().next().getValue();
    }

    private LocalDate getFirstDayOfMonth() {
        return LocalDate.ofEpochDay(System.currentTimeMillis() / (24 * 60 * 60 * 1000) ).withDayOfMonth(1);
    }

    private LocalDate getLastDayOfMonth() {
        return LocalDate.ofEpochDay(System.currentTimeMillis() / (24 * 60 * 60 * 1000) ).plusMonths(1).withDayOfMonth(1).minusDays(1);
    }

    private int getAmountPlayedPerPlayer(List<Game> games, Person player) {
        return (int)games.stream()
                .filter(game -> game.getPerson().equals(player))
                .count();
    }
}
