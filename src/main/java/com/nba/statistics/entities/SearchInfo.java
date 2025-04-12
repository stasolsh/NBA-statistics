package com.nba.statistics.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Iterator;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchInfo {
    private Iterator<Team> teams;
    private Team team;
    private int min;
    private int max;
}
