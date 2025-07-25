package com.gorae.gorae_notification.kafka.consumer.post.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentNotificationEvent {
    public static final String Topic = "comment-notification";

    private String postUserId;
    private String commentUserId;
    private String commentContent;
    private LocalDateTime eventTime;
}
