package com.iade.ulide.models.weak.repositories;

import com.iade.ulide.models.weak.FavSpots;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FavSpotsRepository extends CrudRepository<FavSpots, Integer> {
    @Query(value = "select fs_id from fav_spots where fs_us_id = :usId and fs_sp_id = :spId", nativeQuery = true)
    Integer getFavSpotIdByUserAndSpot(int usId, int spId);
}