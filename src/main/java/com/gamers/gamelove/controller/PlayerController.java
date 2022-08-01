package com.gamers.gamelove.controller;


import java.util.List;

import com.gamers.gamelove.entity.Player;
import com.gamers.gamelove.exception.PlayerNotFoundException;
import com.gamers.gamelove.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @GetMapping(value="/players")
    public List<Player> getAllPlayers(){
        return playerService.getAllPlayers();
    }

    @GetMapping(value="/players/{id}")
    public Player getPlayerById(@PathVariable("id") Integer id) {
        Player player = playerService.findById(id)
                .orElseThrow(()->new PlayerNotFoundException("Player with "+id+" is Not Found!"));
        return player;
    }

    @PostMapping(value="/players")
    public String addPlayer(@RequestBody Player player) {
        return playerService.save(player);
    }

    @PutMapping(value="/players/{id}")
    public String updatePlayer(@PathVariable("id") Integer id, @RequestBody Player newplayer) {
        return playerService.update(id, newplayer);
    }
    @DeleteMapping(value="/players/{id}")
    public String deletePlayer(@PathVariable("id") Integer id) {
        Player player = playerService.findById(id)
                .orElseThrow(()->new PlayerNotFoundException("Player with "+id+" is Not Found!"));
        playerService.deleteById(player.getId());
        return "Player with ID :"+id+" is deleted";
    }

    @PostMapping(value="/players/love/{player}/{game}")
    public String gameLoved(@PathVariable("player") Integer player_id, @PathVariable("game") Integer game_id) {
        return playerService.loved(player_id, game_id);
    }

    @PostMapping(value="/players/unlove/{player}/{game}")
    public String gameUnLoved(@PathVariable("player") Integer player_id, @PathVariable("game") Integer game_id) {
        return playerService.unlove(player_id, game_id);
    }
}
