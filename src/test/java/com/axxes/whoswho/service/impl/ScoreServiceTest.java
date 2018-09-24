package com.axxes.whoswho.service.impl;

import com.axxes.whoswho.model.Game;
import com.axxes.whoswho.model.Person;
import com.axxes.whoswho.model.Score;
import com.axxes.whoswho.model.Sex;
import com.axxes.whoswho.repository.GameRepository;
import com.axxes.whoswho.repository.PersonRepository;
import com.axxes.whoswho.service.ScoreService;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScoreServiceTest {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ScoreService scoreService;


    @Before
    public void setUp() throws Exception {

        ArrayList<Game> playedGames = new ArrayList<>();
        ArrayList<Person> persons = new ArrayList<>();
        Person person1 = new Person("1","Benjamin", "Goesaert", "", Sex.MALE);
        Person person2 = new Person("2","Arne", "Hendrickx", "", Sex.MALE);
        Person person3 = new Person("3","Lieven", "Vetsuypers", "", Sex.MALE);
        Person person4 = new Person("4","Elise", "Van Heylen", "", Sex.FEMALE);
        Person person5 = new Person("5","Vincent", "Reynders", "", Sex.MALE);

        persons.add(person1);
        persons.add(person2);
        persons.add(person3);
        persons.add(person4);
        persons.add(person5);

        personRepository.saveAll(persons);

        playedGames.add(new Game(new ArrayList<>(), 18, LocalDateTime.now().minusSeconds(40), LocalDateTime.now(), person1));
        playedGames.add(new Game(new ArrayList<>(), 20, LocalDateTime.now().minusSeconds(40), LocalDateTime.now(), person1));
        playedGames.add(new Game(new ArrayList<>(), 14, LocalDateTime.now().minusSeconds(40), LocalDateTime.now(), person5));
        playedGames.add(new Game(new ArrayList<>(), 14, LocalDateTime.now().minusSeconds(35), LocalDateTime.now(), person2));
        playedGames.add(new Game(new ArrayList<>(), 18, LocalDateTime.now().minusSeconds(50), LocalDateTime.now(), person3));
        playedGames.add(new Game(new ArrayList<>(), 18, LocalDateTime.now().minusSeconds(40), LocalDateTime.now(), person3));
        playedGames.add(new Game(new ArrayList<>(), 17, LocalDateTime.now().minusSeconds(50), LocalDateTime.now(), person3));
        playedGames.add(new Game(new ArrayList<>(), 16, LocalDateTime.now().minusSeconds(40), LocalDateTime.now(), person4));

        gameRepository.saveAll(playedGames);
    }

    @After
    public void tearDown() {
        gameRepository.deleteAll();
        personRepository.deleteAll();
    }

    @Test
    public void testScoreboardOrder() {
        List<Score> scoreBoard = scoreService.generateScoreBoardMonthly();
        System.out.println(scoreBoard.size());
        System.out.println(Lists.newArrayList(gameRepository.findAll()).size());

        if (scoreBoard.size() != 5) {
            fail("Size scoreboard is wrong!");
        }

        assertThat(scoreBoard.get(0).getFirstName()).isEqualTo("Benjamin");
        assertThat(scoreBoard.get(1).getFirstName()).isEqualTo("Lieven");
        assertThat(scoreBoard.get(1).getPlayTimeInMillis()).isEqualTo(40000.00);
        assertThat(scoreBoard.get(4).getFirstName()).isEqualTo("Vincent");
    }

    @Test
    public void testAmountPlayedPerPlayer() {
        List<Score> scoreBoard = scoreService.generateScoreBoardMonthly();

        if (scoreBoard.size() != 5) {
            fail("Size scoreboard is wrong!");
        }

        scoreBoard.forEach(score -> {
            if (score.getFirstName().equals("Benjamin")) {
                assertThat(score.getAmountPlayed()).isEqualTo(2);
            } else if (score.getFirstName().equals("Lieven")) {
                assertThat(score.getAmountPlayed()).isEqualTo(3);
            } else if (score.getFirstName().equals("Elise")) {
                assertThat(score.getAmountPlayed()).isEqualTo(1);
            }
        });
    }
}