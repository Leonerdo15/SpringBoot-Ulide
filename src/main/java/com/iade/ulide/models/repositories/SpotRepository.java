package com.iade.ulide.models.repositories;

import com.iade.ulide.models.Spot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.HashMap;
import java.util.List;

public interface SpotRepository extends CrudRepository<Spot, Integer> {

    @Query(value = "select avg(se_rate) from users inner join spot_evaluations on us_id = se_us_id inner join spots on sp_id = se_sp_id where sp_id = :id", nativeQuery = true)
    Iterable<Double> spotAverage(int id);

    @Query(value = "select sp_bio from spots where sp_id = :id", nativeQuery = true)
    Iterable<String> findSpBioById(int id);

    @Query(value = "select se_comment from spot_evaluations where se_sp_id = :id", nativeQuery = true)
    Iterable<List<String>> findAllSpotComments(int id);
}