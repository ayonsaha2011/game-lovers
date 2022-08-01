package com.gamers.gamelove.service;

import java.util.List;
import java.util.Optional;

import com.gamers.gamelove.entity.Game;
import com.gamers.gamelove.entity.Player;
import com.gamers.gamelove.exception.PlayerNotFoundException;
import com.gamers.gamelove.repository.GameRepository;
import com.gamers.gamelove.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    GameRepository gameRepository;

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Optional<Player> findById(Integer id) {
        return playerRepository.findById(id);
    }

    public List<Player> findGamesByPlayerId(Integer id) {
        return playerRepository.findGamesById(id);
    }

    public Optional<Player> findByEmail(String email) {
        return playerRepository.findByEmail(email);
    }

    public String save(Player player) {
        try {
            if (playerRepository.existsByEmail(player.getEmail())) {
                return "Player already exists.";
            }
            playerRepository.save(player);
            return "Player record created successfully.";
        }catch (Exception e){
            throw e;
        }
    }

    public String update(Integer id, Player newplayer){
        Player player = playerRepository.findById(id)
                .orElseThrow(()->new PlayerNotFoundException("Player with "+id+" is Not Found!"));
        try {
            player.setEmail(newplayer.getEmail());
            player.setName(newplayer.getName());
            playerRepository.save(player);
            return "Player record updated.";
        } catch (Exception e) {
            throw e;
        }
    }

    public void deleteById(Integer id) {
        playerRepository.deleteById(id);
    }

    public String loved(Integer player_id, Integer game_id) {
        Player player = playerRepository.findById(player_id)
                .orElseThrow(() -> new PlayerNotFoundException("Player with " + player_id + " is Not Found!"));

        Game game = gameRepository.findById(game_id)
                .orElseThrow(() -> new PlayerNotFoundException("Game with " + game_id + " is Not Found!"));
        try {
            if (player != null && game != null) {
                player.addGame(game);
                playerRepository.save(player);

                return "Player record updated.";
            } else {
                return "Player record not found.";
            }
        }
        catch (Exception e) {
            throw e;
        }
    }

    public String unlove(Integer player_id, Integer game_id) {
        Player player = playerRepository.findById(player_id)
                .orElseThrow(()->new PlayerNotFoundException("Player with "+player_id+" is Not Found!"));

        try {
            if (player != null) {
                player.removeGame(game_id);
            }
            playerRepository.save(player);

            return "Player record updated.";
        } catch (Exception e) {
            throw e;
        }

    }
}
