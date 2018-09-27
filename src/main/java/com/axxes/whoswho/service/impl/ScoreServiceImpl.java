package com.axxes.whoswho.service.impl;

import com.axxes.whoswho.model.Game;
import com.axxes.whoswho.model.Person;
import com.axxes.whoswho.model.Score;
import com.axxes.whoswho.repository.GameRepository;
import com.axxes.whoswho.service.ScoreService;
import com.axxes.whoswho.utils.DateTimeUtils;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ScoreServiceImpl implements ScoreService {

    private final GameRepository gameRepository;

    public ScoreServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Score> generateScoreBoardMonthly() {
        List<Game> gamesInCurrentMonth = gameRepository.findByEndTimeBetween(DateTimeUtils.getFirstDayOfMonth(), DateTimeUtils.getLastDayOfMonth());

        Map<String, Score> bestScorePerPlayer = gamesInCurrentMonth
                .stream()
                .map(convertGameToScore(gamesInCurrentMonth))
                .collect(Collectors.groupingBy(Score::getPersonId, Collectors.collectingAndThen(
                        Collectors.reducing(compareScore()),
                        Optional::get)));

        return bestScorePerPlayer.values()
                .stream()
                .sorted(Comparator.comparing(Score::getScore).reversed().thenComparing(Score::getPlayTimeInMillis))
                .collect(Collectors.toList());
    }

    private BinaryOperator<Score> compareScore() {
        return (Score s1, Score s2) -> {
            if (s1.getScore() == s2.getScore()) {
                return s1.getPlayTimeInMillis() < s2.getPlayTimeInMillis() ? s1 : s2;
            } else {
                return s1.getScore() > s2.getScore() ? s1 : s2;
            }
        };
    }

    private Function<Game, Score> convertGameToScore(List<Game> gamesInCurrentMonth) {
        return game -> new Score(
                game.getPerson().getId(),
                game.getPerson().getGivenName(),
                game.getPerson().getSurname(),
                game.getScore(),
                Duration.between(game.getStartTime(), game.getEndTime()).toMillis(),
                getAmountPlayedPerPlayer(gamesInCurrentMonth, game.getPerson()));
    }

    public int getAmountPlayedPerPlayer(List<Game> games, Person player) {
        return (int)games.stream()
                .filter(game -> game.getPerson().equals(player))
                .count();
    }
}
