package com.gorae.gorae_notification.kafka.consumer.user.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEvent {
    public static final String Topic = "user";

    private String action;

    private String userId;
    private String userName;
    private String profileImgUrl;

    private LocalDateTime eventTime;
}
