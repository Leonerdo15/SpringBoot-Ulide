package com.iade.ulide.models.repositories;

import com.iade.ulide.models.RouteEvaluation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RouteEvaluationRepository extends CrudRepository<RouteEvaluation, Integer> {
    @Query(value = "select * from route_evaluations where re_us_id = :id", nativeQuery = true)
    Iterable<RouteEvaluation> findByUserId(int id);
}