package com.gorae.gorae_notification.kafka.consumer.post.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikedNotificationEvent {
    public static final String Topic = "liked-notification";

    private String commentUserId;
    private String commentLikeUserId;
}
