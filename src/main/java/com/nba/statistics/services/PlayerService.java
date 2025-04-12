package com.nba.statistics.services;

import com.nba.statistics.entities.Player;
import com.nba.statistics.repositories.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nba.statistics.entities.Team;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PlayerService {
    private final PlayerRepository playerRepository;

    public Page<Player> findAll(int pageNo, int pageSize) {
        return playerRepository.findAll(PageRequest.of(pageNo - 1, pageSize));
    }

    public List<Player> findPlayersByTeamAndAgeBetween(Team team, int min, int max) {
        return playerRepository.findPlayersByTeamAndAgeBetween(team, min, max);
    }

    public List<Player> findPlayersByNameLike(String name) {
        return playerRepository.findPlayersByNameLike(name + "%");
    }

    public Player save(Player player) {
        return playerRepository.save(player);
    }

    public Player findById(int id) {
        return playerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid player Id:" + id));
    }

    public void delete(Player player) {
        playerRepository.delete(player);
    }
}
