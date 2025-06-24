package com.gorae.gorae_notification.kafka.consumer.post.service;

import com.gorae.gorae_notification.entity.notification.LikedNotificationEntity;
import com.gorae.gorae_notification.entity.user.UserEntity;
import com.gorae.gorae_notification.kafka.consumer.post.dto.LikedChangeEvent;
import com.gorae.gorae_notification.repository.LikedNotificationRepository;
import com.gorae.gorae_notification.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikedChangeService {

    private final UserEntityRepository userEntityRepository;
    private final LikedNotificationRepository likedNotificationRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public void processLikedChangeEvent(LikedChangeEvent event) {
        UserEntity postUserId = userEntityRepository.findByUserId(event.getPostUserId())
                .orElseThrow(()-> new IllegalArgumentException("게시글 유저 없음"));

        UserEntity commentUserId = userEntityRepository.findByUserId(event.getCommentUserId())
                .orElseThrow(()-> new IllegalArgumentException("댓글 유저 없음"));

        UserEntity likedUserId = userEntityRepository.findByUserId(event.getCommentLikeUserId())
                .orElseThrow(()-> new IllegalArgumentException("좋아요 유저 없음"));

        boolean likeStatus = Boolean.parseBoolean(event.getLikeStatus());

        String message = likedUserId.getUserId() + "님이 댓글에 좋아요를 눌렀습니다.";

        LikedNotificationEntity likedNotification = LikedNotificationEntity.builder()
                .postUserId(postUserId)
                .commentUserId(commentUserId)
                .commentLikeUserId(likedUserId)
                .likeStatus(likeStatus)
                .message(message)
                .isRead(false)
                .readAt(null)
                .build();

        likedNotificationRepository.save(likedNotification);

        messagingTemplate.convertAndSend(
                "/topic/notification/liked/" + commentUserId.getUserId(), message);
    }
}
