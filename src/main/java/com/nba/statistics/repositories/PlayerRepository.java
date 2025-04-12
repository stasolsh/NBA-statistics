package com.nba.statistics.repositories;

import com.nba.statistics.entities.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.nba.statistics.entities.Team;

import java.util.List;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer>, PagingAndSortingRepository<Player, Integer> {
    List<Player> findPlayersByTeamAndAgeBetween(Team team, Integer min, Integer max);

    List<Player> findPlayersByNameLike(String name);

}
