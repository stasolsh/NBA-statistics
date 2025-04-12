package com.nba.statistics.controllers;

import com.nba.statistics.entities.Coach;
import com.nba.statistics.services.CoachService;
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
public class CoachController {
    private final CoachService coachService;

    private final TeamService teamService;

    @GetMapping({"/index-coach/{pageNo}", "/index-coach"})
    public String showCoachList(@PathVariable(value = "pageNo", required = false) Integer pageNo, Model model) {
        int pagNumber = Objects.isNull(pageNo) ? 1 : pageNo;
        Page<Coach> page = coachService.findAll(pagNumber, 5);
        model.addAllAttributes(Map.of("coaches", page.getContent(),
                "currentPage", pagNumber,
                "totalPages", page.getTotalPages(),
                "totalItems", page.getTotalElements()));
        return "index-coach";
    }

    @GetMapping("/add/coach")
    public String showSignUpForm(Coach coach, Model model) {
        model.addAttribute("teams", teamService.findAll());
        return "add-coach";
    }

    @PostMapping("/add/coach")
    public String addCoach(@Valid Coach coach, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("teams", teamService.findAll());
            return "add-coach";
        }

        coachService.save(coach);
        return "redirect:/index-coach";
    }

    @GetMapping("/edit/coach/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("coach", coachService.findById(id));
        model.addAttribute("teams", teamService.findAll());
        return "update-coach";
    }

    @PostMapping("/update/coach/{id}")
    public String updateCoach(@PathVariable("id") int id, @Valid Coach coach, BindingResult result, Model model) {
        if (result.hasErrors()) {
            coach.setId(id);
            model.addAttribute("teams", teamService.findAll());
            return "update-coach";
        }

        coachService.save(coach);
        return "redirect:/index-coach";
    }

    @GetMapping("/delete/coach/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model) {
        coachService.delete(coachService.findById(id));
        return "redirect:/index-coach";
    }
}
