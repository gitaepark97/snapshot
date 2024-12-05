package com.spring.snapshot.core.trip.web;

import com.spring.snapshot.core.trip.application.TripService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/trips")
class TripController {

    private final TripService tripService;

    @PostMapping
    TripWithCitiesResponse createTrip(
        @AuthenticationPrincipal Long userId,
        @RequestBody @Valid CreateTripRequest request
    ) {
        return TripWithCitiesResponse.from(tripService.createTrip(userId, request.startTime(), request.endTime(), request.cityIds()));
    }

    @GetMapping
    Page<TripWithWriterAndCitiesResponse> getAllTrip(Pageable pageable) {
        return tripService.getAllTripWithWriterAndCities(pageable).map(TripWithWriterAndCitiesResponse::from);
    }


}
