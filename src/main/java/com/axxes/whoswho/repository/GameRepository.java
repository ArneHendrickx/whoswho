package com.axxes.whoswho.repository;

import com.axxes.whoswho.model.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {
}
