package com.gamers.gamelove.repository;

import java.util.List;
import java.util.Optional;

import com.gamers.gamelove.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
        // Query method
        public boolean existsByEmail(String email);

        public Optional<Player> findByEmail(String email);

        @Query("select max(id) from Player")
        public Integer findMaxId();

        public List<Player> findGamesById(Integer playerId);
}