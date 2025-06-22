package com.gorae.gorae_notification.kafka.consumer.user.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEvent {
    public static final String TOPIC = "user";

    private String userId;
    private String userName;
    private String profileImgUrl;
}
