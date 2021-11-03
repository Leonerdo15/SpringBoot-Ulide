package com.iade.ulide.models.repositories;

import org.springframework.data.repository.CrudRepository;
import com.iade.ulide.models.Spot;

public interface SpotRepository extends CrudRepository<Spot, Integer> {
}