package com.nba.statistics.controllers;

import com.nba.statistics.entities.Team;
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
public class TeamController {
    private final TeamService teamService;

    @GetMapping({"/index-team/{pageNo}", "/index-team"})
    public String showUserList(@PathVariable(value = "pageNo", required = false) Integer pageNo, Model model) {
        int pagNumber = Objects.isNull(pageNo) ? 1 : pageNo;
        Page<Team> page = teamService.findAll(pagNumber, 5);
        model.addAllAttributes(Map.of("teams", page.getContent(),
                "currentPage", pagNumber,
                "totalPages", page.getTotalPages(),
                "totalItems", page.getTotalElements()));
        return "index-team";
    }

    @GetMapping("/add/team")
    public String showSignUpForm(Team team) {
        return "add-team";
    }

    @PostMapping("/add/team")
    public String addTeam(@Valid Team team, BindingResult result) {
        if (result.hasErrors()) {
            return "add-team";
        }

        teamService.save(team);
        return "redirect:/index-team";
    }

    @GetMapping("/edit/team/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("team", teamService.findById(id));
        return "update-team";
    }

    @PostMapping("/update/team/{id}")
    public String updateTeam(@PathVariable("id") int id, @Valid Team team, BindingResult result, Model model) {
        if (result.hasErrors()) {
            team.setId(id);
            return "update-team";
        }

        teamService.save(team);
        return "redirect:/index-team";
    }

    @GetMapping("/delete/team/{id}")
    public String deleteTeam(@PathVariable("id") int id, Model model) {
        teamService.delete(teamService.findById(id));
        return "redirect:/index-team";
    }
}
