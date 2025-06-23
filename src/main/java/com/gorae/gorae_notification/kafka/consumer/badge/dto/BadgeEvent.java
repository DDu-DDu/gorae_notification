package com.gorae.gorae_notification.kafka.consumer.badge.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BadgeEvent {
    public static final String Topic = "badge";



    private LocalDateTime eventTime;
}
