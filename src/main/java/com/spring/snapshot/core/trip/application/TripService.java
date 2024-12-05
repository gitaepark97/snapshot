package com.spring.snapshot.core.trip.application;

import com.spring.snapshot.core.trip.domain.TripWithCities;
import com.spring.snapshot.core.trip.domain.TripWithWriterAndCities;
import com.spring.snapshot.core.user.application.UserReader;
import com.spring.snapshot.core.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TripService {

    private final UserReader userReader;
    private final TripReader tripReader;
    private final TripProcessor tripProcessor;

    public Page<TripWithWriterAndCities> getAllTripWithWriterAndCities(Pageable pageable) {
        return tripReader.getAllTripWithCities(pageable).map(tripWithCities -> {
            User writer = userReader.getUser(tripWithCities.trip().writerId());
            return TripWithWriterAndCities.of(tripWithCities, writer);
        });
    }

    public TripWithCities createTrip(Long userId, Long startTime, Long endTime, List<Long> cityIds) {
        User writer = userReader.getUser(userId);
        return tripProcessor.createTrip(writer.id(), startTime, endTime, cityIds);
    }

}
