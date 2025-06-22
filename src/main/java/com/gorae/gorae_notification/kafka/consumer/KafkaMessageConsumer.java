package com.gorae.gorae_notification.kafka.consumer;

import com.gorae.gorae_notification.kafka.consumer.post.dto.CommentEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaMessageConsumer {
//    @KafkaListener(
//            topics = CommentEvent.Topic,
//            properties = {
//                    JsonDeserializer.VALUE_DEFAULT_TYPE
//                    + "com.gorae.gorae_notification.kafka.consumer.post.dto"}
//    )
//    public void handleCommentRegisterEvent(CommentEvent event, Acknowledgment ack) {
//        try {
//            log.info("userId={}님이 답글을 남겼습니다.", event.getUserId());
//
////            // 알림 생성 서비스 호출
////            String message = event.getUserId() + "님이 게시글 '" + event.getPostTitle() + "'에 답글을 남겼습니다.";
////            notificationService.createNotification(event.getUserId(), message);
//
//            ack.acknowledge();
//        } catch (Exception e) {
//            log.error("답글 작성중 오류가 발생헀습니다. {}", e.getMessage(), e);
//        }
//    }
}
