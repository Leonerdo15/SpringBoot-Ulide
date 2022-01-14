package com.iade.ulide.models.repositories;

import com.iade.ulide.models.Spot;
import com.iade.ulide.models.views.SpotView;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SpotRepository extends CrudRepository<Spot, Integer> {
    String QueryAvgAllSpots = "select sp_name as spName, se_sp_id as id, avg(se_rate) as spAvg\n" +
            "from spot_evaluations inner join spots on se_sp_id = sp_id\n" +
            "group by sp_name, se_sp_id order by spAvg desc";

    @Query(value = "select avg(se_rate) from spot_evaluations where se_sp_id = :id", nativeQuery = true)
    Iterable<Double> spotAverage(int id);

    @Query(value = "select sp_bio from spots where sp_id = :id", nativeQuery = true)
    Iterable<String> findSpBioById(int id);

    @Query(value = "select se_comment from spot_evaluations where se_sp_id = :id", nativeQuery = true)
    Iterable<List<String>> findAllSpotComments(int id);

    @Query(value = "select * from routes inner join route_spots on rt_id = rs_rt_id inner join spots on sp_id = rs_sp_id where rs_rt_id = :id",
            nativeQuery = true)
    Iterable<Spot> findRouteSpots(int id);

    @Query(value = QueryAvgAllSpots, nativeQuery = true)
    Iterable<SpotView> avgAllSpots();

    @Query(value = "select sp.* from spots sp inner join fav_spots on sp_id = fs_sp_id where fs_us_id = :id",
            nativeQuery = true)
    Iterable<Spot> findFavSpotsFromUserId(int id);

    @Query(value = "select * from spots inner join done_spots on sp_id = ds_sp_id where ds_us_id = :id",
            nativeQuery = true)
    Iterable<Spot> findDoneSpotsFromUserId(int id);

    @Query(value = "select * from spots inner join spot_evaluations on sp_id = se_sp_id where se_us_id = :id",
            nativeQuery = true)
    Iterable<Spot> findEvalSpotsFromUserId(int id);


    Optional<Spot> findBySpName(String name);
}