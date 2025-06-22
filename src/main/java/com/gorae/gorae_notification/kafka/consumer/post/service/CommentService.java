package com.gorae.gorae_notification.kafka.consumer.post.service;

import com.gorae.gorae_notification.kafka.consumer.post.dto.CommentEvent;
import com.gorae.gorae_notification.repository.CommentNotificationRepository;
import com.gorae.gorae_notification.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentNotificationRepository commentNotificationRepository;
    public void processCommentEvent(CommentEvent event) {
        CommentEvent commentEvent = new CommentEvent();
//        commentEvent.setUser

    }
}
