package com.spring.snapshot.core.trip.domain;

import java.util.List;

public record TripWithCities(
    Trip trip,
    List<City> cities
) {

    public static TripWithCities of(Trip trip, List<City> cities) {
        return new TripWithCities(trip, cities);
    }

}
