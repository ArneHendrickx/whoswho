package com.axxes.whoswho.controllers;


import com.axxes.whoswho.model.Game;
import com.axxes.whoswho.model.Person;
import com.axxes.whoswho.model.Round;
import com.axxes.whoswho.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

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

    @PostMapping("/practice")
    public Round generatePracticeRound(@RequestBody Person person) {
        return gameService.createPracticeRound(person);
    }

    @PostMapping
    public ResponseEntity<Game> saveGame(@RequestBody Game game) {
        Game g = gameService.saveGame(game);
        return ResponseEntity.created(URI.create("/api/games/"+g.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable long id) {
        Optional<Game> gameOptional = gameService.getGameById(id);
        if (gameOptional.isPresent()) {
            return ResponseEntity.ok(gameOptional.get());
        }

        return ResponseEntity.notFound().build();
    }
}
