package com.gamers.gamelove.service;


import java.util.List;
import java.util.Optional;

import com.gamers.gamelove.entity.Game;
import com.gamers.gamelove.exception.GameNotFoundException;
import com.gamers.gamelove.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GameService {
    @Autowired
    GameRepository gameRepository;


    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public List<Game> getAllLovedGame() {
        return gameRepository.findAllLovedGame();
    }

    public List<Game> findGamesByPlayerId(Integer id) {
        return gameRepository.findGamesByPlayersId(id);
    }
    public List<Game> getMostLovedGames() {
        return gameRepository.findMostLovedGames();
    }

    public Optional<Game> findById(int id) {
        return gameRepository.findById(id);
    }


    public String save(Game game) {
        try {
            gameRepository.save(game);
            return "Game record created successfully.";
        }catch (Exception e){
            throw e;
        }
    }

    public String update(Integer id, Game newgame){
        Game game = gameRepository.findById(id)
                .orElseThrow(()->new GameNotFoundException("Game with "+id+" is Not Found!"));
        try {
            game.setName(newgame.getName());
            game.setDescription(newgame.getDescription());
            gameRepository.save(game);
            return "Game record updated.";
        } catch (Exception e) {
            throw e;
        }
    }

    public void deleteById(Integer id) {
        gameRepository.deleteById(id);
    }

}
