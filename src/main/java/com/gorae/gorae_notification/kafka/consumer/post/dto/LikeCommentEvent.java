package com.gorae.gorae_notification.kafka.consumer.post.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeCommentEvent {
    public static final String TOPIC = "like-comment";

    private String action;

    private String postUserId;
    private String commentUserId;
    private String commentLikeUserId;
    private String likeStatus;
}
