package com.gorae.gorae_notification.kafka.consumer.user.service;

import com.gorae.gorae_notification.entity.user.UserEntity;
import com.gorae.gorae_notification.kafka.consumer.user.dto.UserNotificationChangeEvent;
import com.gorae.gorae_notification.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserNotificationChangeService {

    private final UserRepository userRepository;

    public void processUserChangeEvent(UserNotificationChangeEvent event) {
        UserEntity user = userRepository.findByUserId(event.getUserId())
                .map(existing -> {
                    existing.setUserName(event.getUserName());
                    existing.setProfileImgUrl(event.getProfileImgUrl());
                    return  existing;
                })
                .orElse(UserEntity.builder()
                        .userId(event.getUserId())
                        .userName(event.getUserName())
                        .profileImgUrl(event.getProfileImgUrl())
                        .build()
                );
        userRepository.save(user);
    }
}
