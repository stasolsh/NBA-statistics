package com.nba.statistics.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "coach")
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Coach name is mandatory")
    private String name;
    @Positive(message = "Coach age is mandatory")
    private int age;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    @NotNull(message = "Team is mandatory")
    private Team team;

    public Coach() {
    }

    public Coach(final String name, final int age) {
        this.name = name;
        this.age = age;
    }
}
