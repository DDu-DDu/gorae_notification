package com.gorae.gorae_notification.kafka.consumer.post.service;

import com.gorae.gorae_notification.kafka.consumer.post.dto.CommentEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    public void processCommentEvent(CommentEvent event) {
        CommentEvent commentEvent = new CommentEvent();
//        commentEvent.setUser

    }
}
