package com.gorae.gorae_notification.kafka.consumer.post.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikedEvent {
    public static final String Topic = "liked";

    private String action;

    private String postUserId;
    private String commentUserId;
    private String commentLikeUserId;
    private String likeStatus;

    private LocalDateTime eventTime;
}
