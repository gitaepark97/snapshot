package com.spring.snapshot.core.trip.domain;

import lombok.Builder;

@Builder(toBuilder = true)
public record Trip(
    Long id,
    Long writerId,
    String title,
    String imageName,
    Long startTime,
    Long endTime,
    String description,
    PublishStatus publishStatus,
    Boolean isPublic,
    Long createTime,
    Long updateTime,
    Long deleteTime
) {

    public static Trip of(Long writerId, String title, Long startTime, Long endTime, Long currentTime) {
        return Trip.builder()
            .writerId(writerId)
            .title(title)
            .startTime(startTime)
            .endTime(endTime)
            .publishStatus(PublishStatus.UNPUBLISHED)
            .isPublic(false)
            .createTime(currentTime)
            .updateTime(currentTime)
            .build();
    }

}
