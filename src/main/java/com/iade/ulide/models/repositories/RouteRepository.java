package com.iade.ulide.models.repositories;

import com.iade.ulide.models.Route;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RouteRepository extends CrudRepository<Route, Integer> {

    // show the routes from the most popular to the least
    // The popularity of a route is calculated by the amount of favorites
    @Query(value = "select rt_name from routes, fav_routes where fr_rt_id = rt_id group by rt_name order by count(fr_rt_id) desc", nativeQuery = true)
    Iterable<Route> findAllRoutesSortedByPopularaty();

    Iterable<Route> findRtBioById(int id);

    Iterable<Route> findByRtNameContainingAndRtDistBetween(String name, double min, double max);
}