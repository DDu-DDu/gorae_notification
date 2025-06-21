package com.gorae.gorae_notification.kafka.consumer.post.service;

import com.gorae.gorae_notification.kafka.consumer.post.dto.LikeCommentEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikedCommentService {


    public  void processLikedCommentEvent(LikeCommentEvent event) {

    }
}
