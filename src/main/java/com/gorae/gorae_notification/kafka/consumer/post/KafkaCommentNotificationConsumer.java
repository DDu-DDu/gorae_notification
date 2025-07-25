package com.gorae.gorae_notification.kafka.consumer.post;

import com.gorae.gorae_notification.kafka.consumer.post.dto.CommentNotificationEvent;
import com.gorae.gorae_notification.kafka.consumer.post.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaCommentNotificationConsumer {

    private final CommentService commentService;

    @KafkaListener(
            topics = CommentNotificationEvent.Topic,
            groupId = "comment",
            containerFactory = "commentKafkaListenerFactory"
    )
    public void handleCommentEvent(CommentNotificationEvent event) {
        try {
            log.info("userId={}님이 답글을 남겼습니다.", event.getCommentUserId());
            commentService.processCommentEvent(event);
        } catch (Exception e) {
            log.error("답글 작성중 오류가 발생헀습니다. {}", e.getMessage(), e);
        }
    }
}
