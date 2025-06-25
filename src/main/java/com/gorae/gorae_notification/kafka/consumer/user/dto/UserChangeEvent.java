package com.gorae.gorae_notification.kafka.consumer.user.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserChangeEvent {
    public static final String Topic = "user-notification-change";

    private String userId;
    private String userName;
    private String profileImgUrl;
}
