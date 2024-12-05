package com.spring.snapshot.core.trip.application;

import com.spring.snapshot.core.trip.domain.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CityRepository {

    Page<City> searchAllCityByName(String name, Pageable pageable);

    List<City> findAllCityIdIn(List<Long> ids);

}
