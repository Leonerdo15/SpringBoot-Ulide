package com.iade.ulide.models.weak.repositories;

import com.iade.ulide.models.weak.FavRoutes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FavRoutesRepository extends CrudRepository<FavRoutes, Integer> {
    @Query(value = "select fr_id from fav_routes where fr_us_id = :usId and fr_rt_id = :rtId", nativeQuery = true)
    Integer getFavRouteIdByUserAndRoute(int usId, int rtId);
}