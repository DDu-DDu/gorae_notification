package com.gorae.gorae_notification.kafka.consumer.user.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserNotificationChangeEvent {
    public static final String Topic = "user-notification-change";

    private String userId;
    private String userName;
    private String profileImgUrl;
}
