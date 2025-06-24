package com.gorae.gorae_notification.kafka.consumer.post.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikedChangeEvent {
    public static final String Topic = "liked-notification-change";

    private String postUserId;
    private String commentUserId;
    private String commentLikeUserId;
    private String likeStatus;
}
