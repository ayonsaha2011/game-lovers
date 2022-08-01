package com.gamers.gamelove.controller;

import com.gamers.gamelove.entity.Game;
import com.gamers.gamelove.exception.PlayerNotFoundException;
import com.gamers.gamelove.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GameController {

    @Autowired
    GameService gameService;

    @GetMapping(value="/games")
    public List<Game> getAllGames(){
        return gameService.getAllGames();
    }


    @GetMapping(value="/players/{id}/games")
    public List<Game> findGamesByPlayerId(@PathVariable("id") Integer id) {
        return gameService.findGamesByPlayerId(id);
    }


    @GetMapping(value="/games/loved")
    public List<Game> getAllLovedGame(){
        return gameService.getAllLovedGame();
    }

    @GetMapping(value="/games/most-loved")
    public List<Game> getMostLovedGames(){
        return gameService.getMostLovedGames();
    }


    @GetMapping(value="/games/{id}")
    public Game getGameById(@PathVariable("id") Integer id) {
        Game game = gameService.findById(id)
                .orElseThrow(()->new PlayerNotFoundException("Game with "+id+" is Not Found!"));
        return game;
    }

    @PostMapping(value="/games")
    public String addGame(@RequestBody Game game) {
        return gameService.save(game);
    }

    @PutMapping(value="/games/{id}")
    public String updateGame(@PathVariable("id") Integer id, @RequestBody Game newgame) {
        return gameService.update(id, newgame);
    }
    @DeleteMapping(value="/games/{id}")
    public String deleteGame(@PathVariable("id") Integer id) {
        Game game = gameService.findById(id)
                .orElseThrow(()->new PlayerNotFoundException("Game with "+id+" is Not Found!"));
        gameService.deleteById(game.getId());
        return "Game with ID :"+id+" is deleted";
    }
}
