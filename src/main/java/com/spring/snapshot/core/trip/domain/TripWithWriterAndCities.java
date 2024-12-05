package com.spring.snapshot.core.trip.domain;

import com.spring.snapshot.core.user.domain.User;

import java.util.List;

public record TripWithWriterAndCities(
    Trip trip,
    User writer,
    List<City> cities
) {

    public static TripWithWriterAndCities of(TripWithCities tripWithCities, User writer) {
        return new TripWithWriterAndCities(tripWithCities.trip(), writer, tripWithCities.cities());
    }

}
