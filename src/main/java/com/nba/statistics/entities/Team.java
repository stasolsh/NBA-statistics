package com.nba.statistics.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Team name is mandatory")
    private String name;
    @NotBlank(message = "Team abbreviation is mandatory")
    @Size(max = 10, message = "Team abbreviation not more than 10 characters")
    private String team_abbr;
    @NotBlank(message = "Team location is mandatory")
    private String location;
    @OneToMany(mappedBy = "team")
    private Set<Coach> coaches;
    @OneToMany(mappedBy = "team")
    private Set<Player> players;

    public Team() {
    }

    public Team(String name, String team_abbr, String location) {
        this.name = name;
        this.team_abbr = team_abbr;
        this.location = location;
    }
}
