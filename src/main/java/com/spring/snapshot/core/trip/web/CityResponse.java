package com.spring.snapshot.core.trip.web;

import com.spring.snapshot.core.trip.domain.City;

record CityResponse(
    Long id,
    String name,
    String country
) {

    static CityResponse from(City city) {
        return new CityResponse(city.id(), city.name(), city.country());
    }

}
