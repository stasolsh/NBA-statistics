package com.nba.statistics.repositories;

import com.nba.statistics.entities.Coach;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepository extends CrudRepository<Coach, Integer>, PagingAndSortingRepository<Coach, Integer> {
}
