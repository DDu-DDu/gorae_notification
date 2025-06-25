package com.gorae.gorae_notification.kafka.consumer.post.service;

import com.gorae.gorae_notification.entity.notification.AdoptEntity;
import com.gorae.gorae_notification.entity.user.UserEntity;
import com.gorae.gorae_notification.kafka.consumer.post.dto.AdoptEvent;
import com.gorae.gorae_notification.repository.AdoptNotificationRepository;
import com.gorae.gorae_notification.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdoptService {

    private final UserRepository userRepository;
    private final AdoptNotificationRepository adoptNotificationRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public void processAdoptEvent(AdoptEvent event) {
        UserEntity postUserId = userRepository.findByUserId(event.getPostUserId())
                .orElseThrow(() -> new IllegalArgumentException("질문 작성자 유저 없음"));

        UserEntity commentUserId = userRepository.findByUserId(event.getCommentUserId())
                .orElseThrow(() -> new IllegalArgumentException("댓글 작성자 유저 없음"));


        String message = postUserId.getUserId() + "님이 당신의 댓글을 채택했습니다.";

        AdoptEntity adoptNotification = AdoptEntity.builder()
                .postUserId(postUserId)
                .commentUserId(commentUserId)
                .message(message)
                .isRead(false)
                .readAt(null)
                .build();

        adoptNotificationRepository.save(adoptNotification);

        messagingTemplate.convertAndSend(
                "/topic/notification/adopt/" + commentUserId.getUserId(), message);
    }
}
