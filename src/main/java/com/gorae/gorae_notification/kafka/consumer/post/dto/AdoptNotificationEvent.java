package com.gorae.gorae_notification.kafka.consumer.post.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdoptNotificationEvent {
    public static final String Topic = "adopt-notification";

    private String postUserId;
    private String commentUserId;
}
