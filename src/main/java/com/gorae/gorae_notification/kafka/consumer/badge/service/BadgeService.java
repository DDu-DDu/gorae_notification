package com.gorae.gorae_notification.kafka.consumer.badge.service;

import com.gorae.gorae_notification.entity.notification.BadgeEntity;
import com.gorae.gorae_notification.entity.user.UserEntity;
import com.gorae.gorae_notification.kafka.consumer.badge.dto.BadgeEvent;
import com.gorae.gorae_notification.repository.BadgeNotificationRepository;
import com.gorae.gorae_notification.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BadgeService {

    private final UserRepository userRepository;
    private final BadgeNotificationRepository badgeNotificationRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public void processBadgeEvent(BadgeEvent event) {
        UserEntity badgeUserId = userRepository.findByUserId(event.getBadgeUserId())
                .orElseThrow(() -> new IllegalArgumentException("뱃지 유저 없음"));

        String code = event.getCode();

        String name = event.getName();

        String icon = event.getIcon();

        String message = badgeUserId.getUserId() + "님 뱃지가 달렸습니다.";

        BadgeEntity badgeNotification = BadgeEntity.builder()
                .badgeUserId(badgeUserId)
                .code(code)
                .name(name)
                .icon(icon)
                .message(message)
                .isRead(false)
                .readAt(null)
                .build();

        badgeNotificationRepository.save(badgeNotification);

        simpMessagingTemplate.convertAndSend(
                "/topic/notification/badge/" + badgeUserId.getUserId(), message
        );
    }
}
