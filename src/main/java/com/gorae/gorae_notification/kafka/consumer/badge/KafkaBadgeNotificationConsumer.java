package com.gorae.gorae_notification.kafka.consumer.badge;

import com.gorae.gorae_notification.kafka.consumer.badge.dto.BadgeNotificationEvent;
import com.gorae.gorae_notification.kafka.consumer.badge.service.BadgeNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaBadgeNotificationConsumer {

    private final BadgeNotificationService badgeNotificationService;

    @KafkaListener(
            topics = BadgeNotificationEvent.Topic,
            groupId = "Badge-notification",
            containerFactory = "badgeKafkaListenerFactory"
    )
    public void handleBadgeEvent(BadgeNotificationEvent event) {
        try{
            log.info("userId={}님 icon={}가 생성되었습니다.", event.getBadgeUserId(), event.getIcon());
            badgeNotificationService.processBadgeEvent(event);
        } catch (Exception e) {
            log.error("뱃지 생성중 오류가 발생했습니다. {}",e.getMessage(), e);
        }
    }
}
