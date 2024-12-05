package com.spring.snapshot.core.trip.web;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

record CreateTripRequest(
    @NotNull
    Long startTime,

    @NotNull
    Long endTime,

    @NotEmpty
    List<@NotNull Long> cityIds
) {

}
