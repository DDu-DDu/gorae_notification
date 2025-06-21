package com.gorae.gorae_notification.kafka.consumer.post.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class zzz {
    public static final String Topic = "comment-register";

    private String action;

    private String userId;
    private String postId;
    private String postTitle;
    private String comment;

    private LocalDateTime eventTime;
}
