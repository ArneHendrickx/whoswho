package com.axxes.whoswho.controllers;


import com.axxes.whoswho.model.Game;
import com.axxes.whoswho.model.Person;
import com.axxes.whoswho.model.Sex;
import com.axxes.whoswho.service.GameService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/create")
    public Game generateGame(@RequestBody Person person) {
        return gameService.createGameForPerson(person);
    }

    @PostMapping
    public Game saveGame(RequestBody Game game) {
        return gameService.save(game);
    }
}
