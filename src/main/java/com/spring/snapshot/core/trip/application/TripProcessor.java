package com.spring.snapshot.core.trip.application;

import com.spring.snapshot.common.infrastructure.ClockProvider;
import com.spring.snapshot.core.trip.domain.City;
import com.spring.snapshot.core.trip.domain.Trip;
import com.spring.snapshot.core.trip.domain.TripWithCities;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
class TripProcessor {

    private static final String TITLE_POSTFIX = " 여행";

    private final ClockProvider clockProvider;
    private final TripRepository tripRepository;
    private final CityRepository cityRepository;

    @Transactional
    TripWithCities createTrip(Long writerId, Long startTime, Long endTime, List<Long> cityIds) {
        List<City> cities = cityRepository.findAllCityIdIn(cityIds);

        String title = generateTitle(cities);
        Trip trip = Trip.of(writerId, title, startTime, endTime, clockProvider.millis());
        trip = tripRepository.saveTrip(trip);

        tripRepository.saveTripCities(trip.id(), cities.stream().map(City::id).toList());
        return TripWithCities.of(trip, cities);
    }

    private String generateTitle(List<City> cities) {
        return cities.getFirst().name() + TITLE_POSTFIX;
    }

}
