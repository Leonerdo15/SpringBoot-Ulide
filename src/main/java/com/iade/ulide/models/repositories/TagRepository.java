package com.iade.ulide.models.repositories;

import com.iade.ulide.models.Tag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Integer> {
    @Query(value = "select * from users_spots_tags inner join tags on tg_id = ust_tg_id and ust_sp_id = :id",
            nativeQuery = true)
    Iterable<Tag> getTagsFromSpotId(int id);

    @Query(value = "select * from users_spots_tags inner join tags on tg_id = ust_tg_id and ust_us_id = :id",
            nativeQuery = true)
    Iterable<Tag> getTagsFromUserId(int id);
}