package com.gorae.gorae_notification.kafka.consumer.user.service;

import com.gorae.gorae_notification.kafka.consumer.post.dto.LikeCommentEvent;
import com.gorae.gorae_notification.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    public  void processUserEvent(LikeCommentEvent event) {

    }
}
