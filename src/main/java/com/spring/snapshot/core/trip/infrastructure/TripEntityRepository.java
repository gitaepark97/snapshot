package com.spring.snapshot.core.trip.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

interface TripEntityRepository extends JpaRepository<TripEntity, Long> {

}
