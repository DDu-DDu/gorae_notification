package com.gorae.gorae_notification.kafka.consumer.post.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdoptEvent {
    public static final String Topic = "adopt";

    private String action;

    private String postUserId;
    private String commentUserId;
    private String adopt;

    private LocalDateTime eventTime;
}
