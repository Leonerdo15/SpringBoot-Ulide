package com.iade.ulide.models.weak.repositories;

import com.iade.ulide.models.Spot;
import com.iade.ulide.models.weak.FavSpots;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FavSpotsRepository extends CrudRepository<FavSpots, Integer> {
    @Query(value = "select sp.* from spots sp inner join fav_spots on sp_id = fs_sp_id where fs_us_id = :id",
            nativeQuery = true)
    Iterable<String> findFavSpotByUserId(int id);
}