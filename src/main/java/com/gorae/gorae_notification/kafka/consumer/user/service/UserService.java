package com.gorae.gorae_notification.kafka.consumer.user.service;

import com.gorae.gorae_notification.entity.user.UserEntity;
import com.gorae.gorae_notification.kafka.consumer.post.dto.LikedEvent;
import com.gorae.gorae_notification.kafka.consumer.user.dto.UserEvent;
import com.gorae.gorae_notification.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userEntityRepository;

    public void saveOrUpdateUser(UserEvent event) {
        UserEntity user = userEntityRepository.findByUserId(event.getUserId())
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
        userEntityRepository.save(user);
    }
}
