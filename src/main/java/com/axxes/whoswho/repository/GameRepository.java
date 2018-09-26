package com.axxes.whoswho.repository;

import com.axxes.whoswho.model.Game;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface GameRepository extends CrudRepository<Game, Long> {

    @Override
    List<Game> findAll();
    List<Game> findByEndTimeBetween(LocalDateTime start, LocalDateTime end);
}
