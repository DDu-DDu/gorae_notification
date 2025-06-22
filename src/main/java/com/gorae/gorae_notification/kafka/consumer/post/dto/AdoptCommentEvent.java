package com.gorae.gorae_notification.kafka.consumer.post.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdoptCommentEvent {
    public static final String TOPIC = "adopt-comment";

    private String action;

    private String postUserId;
    private String adopt;
}
