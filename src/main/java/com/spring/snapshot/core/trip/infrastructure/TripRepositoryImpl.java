package com.spring.snapshot.core.trip.infrastructure;

import com.spring.snapshot.core.trip.application.TripRepository;
import com.spring.snapshot.core.trip.domain.City;
import com.spring.snapshot.core.trip.domain.Trip;
import com.spring.snapshot.core.trip.domain.TripWithCities;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
class TripRepositoryImpl implements TripRepository {

    private final TripEntityRepository tripEntityRepository;
    private final TripCityEntityRepository tripCityEntityRepository;
    private final CityEntityRepository cityEntityRepository;

    @Override
    public Page<TripWithCities> findAllTripWithCities(Pageable pageable) {
        return tripEntityRepository.findAll(pageable).map(TripEntity::toTrip).map(
            trip -> {
                List<Long> cityIds = tripCityEntityRepository.findByTripId(trip.id())
                    .stream()
                    .map(TripCityEntity::getCityId)
                    .toList();
                List<City> cities = cityEntityRepository.findAllByIdIn(cityIds)
                    .stream()
                    .map(CityEntity::toCity)
                    .toList();
                return TripWithCities.of(trip, cities);
            }
        );
    }

    @Override
    public Trip saveTrip(Trip trip) {
        return tripEntityRepository.save(TripEntity.from(trip)).toTrip();
    }

    @Transactional
    @Override
    public void saveTripCities(Long tripId, List<Long> cityIds) {
        tripCityEntityRepository.saveAll(cityIds.stream().map(cityId -> TripCityEntity.of(tripId, cityId)).toList());
    }

}
