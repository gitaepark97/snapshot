package com.spring.snapshot.core.trip.application;

import com.spring.snapshot.core.trip.domain.City;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
class CityReader {

    private final CityRepository cityRepository;

    Page<City> searchAllCity(String name, Pageable pageable) {
        return cityRepository.searchAllCityByName(name, pageable);
    }

}
