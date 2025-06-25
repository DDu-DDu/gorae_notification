package com.gorae.gorae_notification.kafka.consumer.badge.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BadgeNotificationEvent {
    public static final String Topic = "badge-notification";

    private String badgeUserId;
    private String code;
    private String name;
    private String icon;
    private LocalDateTime eventTime;
}
