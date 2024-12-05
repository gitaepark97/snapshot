package com.spring.snapshot.core.trip.infrastructure;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity(name = "\"trip_city\"")
@Table(indexes = {
    @Index(columnList = "tripId, cityId", unique = true),
})
class TripCityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long tripId;

    @Getter
    private Long cityId;

    static TripCityEntity of(Long tripId, Long cityId) {
        return new TripCityEntity(null, tripId, cityId);
    }

}
