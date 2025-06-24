package com.gorae.gorae_notification.kafka.consumer.post.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdoptChangeEvent {
    public static final String Topic = "adopt-notification-change";

    private String postUserId;
    private String commentUserId;
    private String adopt;
}
