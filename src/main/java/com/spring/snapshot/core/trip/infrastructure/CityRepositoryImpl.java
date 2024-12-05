package com.spring.snapshot.core.trip.infrastructure;

import com.spring.snapshot.core.trip.application.CityRepository;
import com.spring.snapshot.core.trip.domain.City;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
class CityRepositoryImpl implements CityRepository {

    private final CityEntityRepository cityEntityRepository;

    @Override
    public Page<City> searchAllCityByName(String name, Pageable pageable) {
        CityEntity cityEntity = CityEntity.builder().name(name).build();

        ExampleMatcher matcher = ExampleMatcher.matching()
            .withStringMatcher(ExampleMatcher.StringMatcher.STARTING)
            .withIgnoreNullValues();
        Example<CityEntity> example = Example.of(cityEntity, matcher);

        return cityEntityRepository.findAll(example, pageable).map(CityEntity::toCity);
    }

    @Override
    public List<City> findAllCityIdIn(List<Long> ids) {
        return cityEntityRepository.findAllByIdIn(ids).stream().map(CityEntity::toCity).toList();
    }

}
