package com.spring.snapshot.core.trip.web;

import com.spring.snapshot.core.trip.domain.TripWithCities;
import com.spring.snapshot.core.trip.domain.TripWithWriterAndCities;
import com.spring.snapshot.core.user.domain.User;

import java.util.List;

record TripWithCitiesResponse(
    Long id,
    String title,
    String imageName,
    Long startTime,
    Long endTime,
    String description,
    Long updateTime,
    List<CityResponse> cities
) {

    static TripWithCitiesResponse from(TripWithCities tripWithCities) {
        return new TripWithCitiesResponse(
            tripWithCities.trip().id(),
            tripWithCities.trip().title(),
            tripWithCities.trip().imageName(),
            tripWithCities.trip().startTime(),
            tripWithCities.trip().endTime(),
            tripWithCities.trip().description(),
            tripWithCities.trip().updateTime(),
            tripWithCities.cities().stream().map(CityResponse::from).toList()
        );
    }

}

record TripWithWriterAndCitiesResponse(
    Long id,
    String title,
    String imageName,
    Long startTime,
    Long endTime,
    String description,
    Long updateTime,
    WriterResponse writerResponse,
    List<CityResponse> cities
) {

    static TripWithWriterAndCitiesResponse from(TripWithWriterAndCities tripWithWriterAndCities) {
        return new TripWithWriterAndCitiesResponse(
            tripWithWriterAndCities.trip().id(),
            tripWithWriterAndCities.trip().title(),
            tripWithWriterAndCities.trip().imageName(),
            tripWithWriterAndCities.trip().startTime(),
            tripWithWriterAndCities.trip().endTime(),
            tripWithWriterAndCities.trip().description(),
            tripWithWriterAndCities.trip().updateTime(),
            WriterResponse.from(tripWithWriterAndCities.writer()),
            tripWithWriterAndCities.cities().stream().map(CityResponse::from).toList()
        );
    }

}

record WriterResponse(
    Long id,
    String nickname,
    String imageUrl
) {

    static WriterResponse from(User writer) {
        return new WriterResponse(writer.id(), writer.nickname(), writer.imageUrl());
    }

}