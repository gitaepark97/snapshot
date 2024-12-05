package com.spring.snapshot.core.trip.infrastructure;

import com.spring.snapshot.core.trip.domain.PublishStatus;
import com.spring.snapshot.core.trip.domain.Trip;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity(name = "\"trip\"")
@DynamicInsert
@DynamicUpdate
class TripEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long writerId;

    private String title;

    private String imageName;

    private Long startTime;

    private Long endTime;

    private String description;

    @Enumerated(EnumType.STRING)
    private PublishStatus publishStatus;

    private Boolean isPublic;

    private Long createTime;

    private Long updateTime;

    private Long deleteTime;

    static TripEntity from(Trip trip) {
        return new TripEntity(
            trip.id(),
            trip.writerId(),
            trip.title(),
            trip.imageName(),
            trip.startTime(),
            trip.endTime(),
            trip.description(),
            trip.publishStatus(),
            trip.isPublic(),
            trip.createTime(),
            trip.updateTime(),
            trip.deleteTime()
        );
    }

    Trip toTrip() {
        return Trip.builder()
            .id(id)
            .writerId(writerId)
            .title(title)
            .imageName(imageName)
            .startTime(startTime)
            .endTime(endTime)
            .description(description)
            .publishStatus(publishStatus)
            .isPublic(isPublic)
            .createTime(createTime)
            .updateTime(updateTime)
            .deleteTime(deleteTime)
            .build();
    }

}
