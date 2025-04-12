package com.nba.statistics.services;

import com.nba.statistics.entities.Coach;
import com.nba.statistics.repositories.CoachRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CoachService {
    private final CoachRepository coachRepository;

    public Page<Coach> findAll(int pageNo, int pageSize) {
        return coachRepository.findAll(PageRequest.of(pageNo - 1, pageSize));
    }

    public Coach save(Coach coach) {
        return coachRepository.save(coach);
    }

    public Coach findById(int id) {
        return coachRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid coach Id:" + id));
    }

    public void delete(Coach coach) {
        coachRepository.delete(coach);
    }
}
