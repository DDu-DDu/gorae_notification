package com.gorae.gorae_notification.kafka.consumer.post;

import com.gorae.gorae_notification.kafka.consumer.post.dto.CommentChangeEvent;
import com.gorae.gorae_notification.kafka.consumer.post.service.CommentChangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaCommentChangeConsumer {

    private final CommentChangeService commentChangeService;

    @KafkaListener(
            topics = CommentChangeEvent.Topic,
            groupId = "comment-change",
            containerFactory = "commentChangeKafkaListenerFactory"
    )
    public void handleCommentChangeEvent(CommentChangeEvent event, Acknowledgment ack) {
        try {
            log.info("userId={}님이 답글을 남겼습니다.", event.getCommentUserId());
            commentChangeService.processCommentChangeEvent(event);
            ack.acknowledge();
        } catch (Exception e) {
            log.error("답글 작성중 오류가 발생헀습니다. {}", e.getMessage(), e);
        }
    }
}
