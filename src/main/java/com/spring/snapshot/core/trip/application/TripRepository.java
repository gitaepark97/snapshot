package com.spring.snapshot.core.trip.application;

import com.spring.snapshot.core.trip.domain.Trip;
import com.spring.snapshot.core.trip.domain.TripWithCities;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TripRepository {

    Page<TripWithCities> findAllTripWithCities(Pageable pageable);

    Trip saveTrip(Trip trip);

    void saveTripCities(Long tripId, List<Long> cityIds);

}
