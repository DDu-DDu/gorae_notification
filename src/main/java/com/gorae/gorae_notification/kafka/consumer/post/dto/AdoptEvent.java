package com.gorae.gorae_notification.kafka.consumer.post.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdoptEvent {
    public static final String Topic = "adopt-comment";

    private String action;

    private String commentUserId;
    private String postUserId;
    private String adopt;

    private LocalDateTime eventTime;
}
