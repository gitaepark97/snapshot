package com.spring.snapshot.core.trip.infrastructure;

import com.spring.snapshot.core.trip.domain.City;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity(name = "\"city\"")
class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String country;

    private BigDecimal latitude;

    private BigDecimal longitude;

    City toCity() {
        return City.builder()
            .id(id)
            .name(name)
            .country(country)
            .latitude(latitude)
            .longitude(longitude)
            .build();
    }

}
