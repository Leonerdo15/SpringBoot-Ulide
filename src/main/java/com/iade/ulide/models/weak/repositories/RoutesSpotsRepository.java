package com.iade.ulide.models.weak.repositories;

import com.iade.ulide.models.Spot;
import com.iade.ulide.models.weak.RoutesSpots;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RoutesSpotsRepository extends CrudRepository<RoutesSpots, Integer> {
    Iterable<RoutesSpots> findByRsRtId(int id);

    @Query(value = "select sp_id, sp_name, sp_lat, sp_long, sp_price, sp_bio from routes inner join route_spots on rt_id = rs_rt_id inner join spots on sp_id = rs_sp_id where rs_rt_id = :id", nativeQuery = true)
    Iterable<Spot> findRouteSpots(int id);
}