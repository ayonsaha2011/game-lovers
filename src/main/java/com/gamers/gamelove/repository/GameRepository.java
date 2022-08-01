package com.gamers.gamelove.repository;

import java.util.List;
import java.util.Optional;

import com.gamers.gamelove.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

    @Query("select max(id) from Game")
    public Integer findMaxId();

    List<Game> findGamesByPlayersId(Integer id);

    @Query("select g from Game g left join g.players p group by g.id having count(p) > 0 ")
    List<Game> findAllLovedGame();


    @Query("select g, count(p) as player_count from Game g left join g.players p group by g.id having count(p) > 0 ORDER BY player_count Desc")
    List<Game> findMostLovedGames();
}
