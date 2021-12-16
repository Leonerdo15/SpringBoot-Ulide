package com.iade.ulide.models.repositories;

import com.iade.ulide.models.Route;
import com.iade.ulide.models.views.RouteView;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//😊😂🙌😎🤞🎂
public interface RouteRepository extends CrudRepository<Route, Integer> {

    String QueryAvgAllRoutes = "select rt_name as rtName, re_rt_id as id, avg(re_rate) as rtAvg\n" +
            "from route_evaluations inner join routes on re_rt_id = rt_id\n" +
            "group by rt_name, re_rt_id order by rtAvg desc";

    // show the routes from the most popular to the least
    // The popularity of a route is calculated by the amount of favorites
    @Query(value = "select * from routes, fav_routes where fr_rt_id = rt_id group by rt_name, rt_id, rt_bio, rt_dist, fr_id, fr_us_id, fr_rt_id order by count(fr_rt_id) desc", nativeQuery = true)
    Iterable<Route> findAllRoutesSortedByPopularity();

    @Query(value = "select rt_bio from routes where rt_id = :id", nativeQuery = true)
    Iterable<String> findRtBioById(int id);

    Iterable<Route> findByRtNameContainingAndRtDistBetween(String name, double min, double max);

    @Query(value = QueryAvgAllRoutes, nativeQuery = true)
    Iterable<RouteView> avgAllRoutes();

    @Query(value = "select rt.* from routes rt inner join fav_routes on rt_id = fr_rt_id where fr_us_id = :id",
            nativeQuery = true)
    Iterable<Route> findFavRoutesFromUserId(int id);

    @Query(value = "select * from routes inner join done_routes on rt_id = dr_rt_id where dr_us_id = :id",
            nativeQuery = true)
    Iterable<Route> findDoneRoutesFromUserId(int id);

    @Query(value = "select * from routes inner join route_evaluations on rt_id = re_rt_id where re_us_id = :id",
            nativeQuery = true)
    Iterable<Route> findEvalRoutesFromUserId(int id);
}