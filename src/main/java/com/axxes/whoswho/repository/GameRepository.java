package com.axxes.whoswho.repository;

import com.axxes.whoswho.model.Game;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface GameRepository extends CrudRepository<Game, Long> {
    List<Game> findByEndTimeBetween(LocalDate start, LocalDate end);
}
