package com.gorae.gorae_notification.kafka.consumer.badge;

import com.gorae.gorae_notification.kafka.consumer.badge.dto.BadgeEvent;
import com.gorae.gorae_notification.kafka.consumer.badge.service.BadgeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaBadgeConsumer {

    private final BadgeService badgeService;

    @KafkaListener(
            topics = BadgeEvent.Topic,
            groupId = "Badge-notification",
            containerFactory = "badgeKafkaListenerFactory"
    )
    public void handleBadgeEvent(BadgeEvent event, Acknowledgment ack) {
        try{
            log.info("userId={}님 icon={}가 생성되었습니다.", event.getBadgeUserId(), event.getIcon());
            badgeService.processBadgeEvent(event);
            ack.acknowledge();
        } catch (Exception e) {
            log.error("뱃지 생성중 오류가 발생했습니다. {}",e.getMessage(), e);
        }
    }
}
