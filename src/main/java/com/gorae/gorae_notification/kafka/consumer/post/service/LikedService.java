package com.gorae.gorae_notification.kafka.consumer.post.service;

import com.gorae.gorae_notification.entity.notification.LikedEntity;
import com.gorae.gorae_notification.entity.user.UserEntity;
import com.gorae.gorae_notification.kafka.consumer.post.dto.LikedNotificationEvent;
import com.gorae.gorae_notification.repository.LikedNotificationRepository;
import com.gorae.gorae_notification.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikedService {

    private final UserRepository userRepository;
    private final LikedNotificationRepository likedNotificationRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public void processLikedEvent(LikedNotificationEvent event) {

        UserEntity commentUserId = userRepository.findByUserId(event.getCommentUserId())
                .orElseThrow(()-> new IllegalArgumentException("댓글 유저 없음"));

        UserEntity likedUserId = userRepository.findByUserId(event.getCommentLikeUserId())
                .orElseThrow(()-> new IllegalArgumentException("좋아요 유저 없음"));

        String message = likedUserId.getUserId() + "님이 댓글에 좋아요를 눌렀습니다.";

        LikedEntity likedNotification = LikedEntity.builder()
                .commentUserId(commentUserId)
                .commentLikeUserId(likedUserId)
                .message(message)
                .isRead(false)
                .readAt(null)
                .build();

        likedNotificationRepository.save(likedNotification);

        messagingTemplate.convertAndSend(
                "/topic/notification/liked/" + commentUserId.getUserId(), message);
    }
}
