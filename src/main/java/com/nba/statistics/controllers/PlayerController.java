package com.nba.statistics.controllers;

import com.nba.statistics.entities.Player;
import com.nba.statistics.entities.SearchInfo;
import com.nba.statistics.entities.Team;
import com.nba.statistics.services.PlayerService;
import com.nba.statistics.services.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;
    private final TeamService teamService;

    @GetMapping({"/index-player/{pageNo}", "/index-player"})
    public String showUserList(@PathVariable(value = "pageNo", required = false) Integer pageNo, Model model) {
        int pagNumber = Objects.isNull(pageNo) ? 1 : pageNo;
        Page<Player> page = playerService.findAll(pagNumber, 10);
        model.addAllAttributes(Map.of("players", page.getContent(),
                "currentPage", pagNumber,
                "totalPages", page.getTotalPages(),
                "totalItems", page.getTotalElements(),
                "keyword", ""));
        return "index-player";
    }

    @GetMapping("/add/player")
    public String showSignUpForm(Player player, Model model) {
        model.addAttribute("teams", teamService.findAll());
        return "add-player";
    }

    @PostMapping("/add/player")
    public String addPlayer(@Valid Player player, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("teams", teamService.findAll());
            return "add-player";
        }

        playerService.save(player);
        return "redirect:/index-player";
    }

    @GetMapping("/edit/player/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("player", playerService.findById(id));
        model.addAttribute("teams", teamService.findAll());
        return "update-player";
    }

    @PostMapping("/update/player/{id}")
    public String updatePlayer(@PathVariable("id") int id, @Valid Player player, BindingResult result, Model model) {
        if (result.hasErrors()) {
            player.setId(id);
            model.addAttribute("teams", teamService.findAll());
            return "update-player";
        }

        playerService.save(player);
        return "redirect:/index-player";
    }

    @GetMapping("/delete/player/{id}")
    public String deletePlayer(@PathVariable("id") int id, Model model) {
        playerService.delete(playerService.findById(id));
        return "redirect:/index-player";
    }

    @GetMapping("/index-player-statistics")
    public String showSignUpForm(Model model) {
        SearchInfo searchInfo = new SearchInfo(teamService.findAll(), new Team(), 16, 45);
        model.addAttribute("searchInfo", searchInfo);
        return "index-player-statistics";
    }

    @GetMapping("/player-statistics")
    public String showPlayerStatistics(SearchInfo searchInfo, Model model) {
        model.addAllAttributes(Map.of("players",
                playerService.findPlayersByTeamAndAgeBetween(searchInfo.getTeam(), searchInfo.getMin(), searchInfo.getMax())));
        return "index-player";
    }

    @GetMapping("/filter-player")
    public String filterPlayersByName(String keyword, Model model) {
        model.addAllAttributes(Map.of("players",
                playerService.findPlayersByNameLike(keyword)));
        return "index-player";
    }
}
