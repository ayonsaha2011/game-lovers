package com.gamers.gamelove.entity;

import javax.persistence.*;

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
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String description;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "games")
    private List<Player> players;


}
