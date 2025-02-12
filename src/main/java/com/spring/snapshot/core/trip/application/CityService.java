package com.spring.snapshot.core.trip.application;

import com.spring.snapshot.core.trip.domain.City;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CityService {

    private final CityReader cityReader;

    public Page<City> searchAllCity(String name, Pageable pageable) {
        return cityReader.searchAllCity(name, pageable);
    }

}
