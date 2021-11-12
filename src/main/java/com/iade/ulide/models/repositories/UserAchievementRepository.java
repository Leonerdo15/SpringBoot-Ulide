package com.iade.ulide.models.repositories;

import com.iade.ulide.models.UserAchievement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserAchievementRepository extends CrudRepository<UserAchievement, Integer> {
    String QueryFindUserAchievementsByUaUsId2 = "select count(*) from user_achievements";

    Iterable<UserAchievement> findUserAchievementsByUaUsId(int id);

    @Query(value = "select ac.ac_id, ua.ua_id, ac.ac_name, ua_ac_id, ua_date, ua_us_id from user_achievements ua, achievements ac where ua.ua_ac_id = ac.ac_id and ua.ua_us_id = ?1"  , nativeQuery = true)
    Iterable<UserAchievement> findUserAchievementsByUaUsId2(int id);

    long countByUaAcId(Integer uaAcId);




}