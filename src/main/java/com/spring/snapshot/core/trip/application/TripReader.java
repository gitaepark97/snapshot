package com.spring.snapshot.core.trip.application;

import com.spring.snapshot.core.trip.domain.TripWithCities;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
class TripReader {

    private final TripRepository tripRepository;

    Page<TripWithCities> getAllTripWithCities(Pageable pageable) {
        return tripRepository.findAllTripWithCities(pageable);
    }

}
