package com.iade.ulide.models.weak.repositories;

import com.iade.ulide.models.Route;
import com.iade.ulide.models.weak.FavRoutes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FavRoutesRepository extends CrudRepository<FavRoutes, Integer> {
    @Query(value = "select * from routes inner join fav_routes on rt_id = fr_rt_id where fr_us_id = :id",
            nativeQuery = true)
    Iterable<Route> findFavRouteByUserId(int id);
}