package com.spring.snapshot.core.trip.web;

import com.spring.snapshot.core.trip.application.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cities")
class CityController {

    private final CityService cityService;

    @GetMapping
    Page<CityResponse> searchAllCity(@RequestParam(required = false) String name, Pageable pageable) {
        return cityService.searchAllCity(name, pageable).map(CityResponse::from);
    }

}
