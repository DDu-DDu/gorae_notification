package com.gorae.gorae_notification.kafka.consumer.post.service;

import com.gorae.gorae_notification.entity.notification.CommentNotificationEntity;
import com.gorae.gorae_notification.entity.user.UserEntity;
import com.gorae.gorae_notification.kafka.consumer.post.dto.CommentEvent;
import com.gorae.gorae_notification.repository.CommentNotificationRepository;
import com.gorae.gorae_notification.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final UserEntityRepository userEntityRepository;
    private final CommentNotificationRepository commentNotificationRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public void processCommentEvent(CommentEvent event) {
        UserEntity postUserId = userEntityRepository.findByUserId(event.getPostUserId())
                .orElseThrow(() -> new IllegalArgumentException("게시글 유저 없음"));

        UserEntity commentUserId = userEntityRepository.findByUserId(event.getCommentUserId())
                .orElseThrow(() -> new IllegalArgumentException("댓글 유저 없음"));

        String message = commentUserId.getUserId() + "님이 당신의 글에 댓글을 남겼습니다.";

        CommentNotificationEntity commentNotification = CommentNotificationEntity.builder()
                .postUserId(postUserId)
                .commentUserId(commentUserId)
                .commentContent(event.getCommentContent())
                .message(message)
                .isRead(false)
                .readAt(null)
                .build();

        commentNotificationRepository.save(commentNotification);

        messagingTemplate.convertAndSend(
                "/topic/notification/comment/" + postUserId.getUserId(), message);
    }
}
