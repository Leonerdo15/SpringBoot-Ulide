package com.iade.ulide.models.repositories;

import com.iade.ulide.models.SpotEvaluation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SpotEvaluationRepository extends CrudRepository<SpotEvaluation, Integer> {
    @Query(value = "select * from spot_evaluations where se_us_id = :id", nativeQuery = true)
    Iterable<SpotEvaluation> findByUserId(int id);
}