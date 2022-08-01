package com.gamers.gamelove.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String email;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "game_player",
            joinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "player_id",
                    referencedColumnName = "id"))
    @JsonIgnore
    private List<Game> games;

    // getters and setters
    public void addGame(Game game) {
        this.games.add(game);
        game.getPlayers().add(this);
    }

    public void removeGame(Integer gameId) {
        Game game = this.games.stream().filter(g -> g.getId() == gameId).findFirst().orElse(null);
        if (game != null) {
            this.games.remove(game);
            game.getPlayers().remove(this);
        }
    }

}
