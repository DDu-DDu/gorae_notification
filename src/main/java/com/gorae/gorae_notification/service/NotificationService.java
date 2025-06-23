package com.gorae.gorae_notification.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.gorae.gorae_notification.dto.NotificationDto;
import com.gorae.gorae_notification.repository.AdoptNotificationRepository;
import com.gorae.gorae_notification.repository.CommentNotificationRepository;
import com.gorae.gorae_notification.repository.LikedNotificationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final LikedNotificationRepository likedNotificationRepository;
    private final CommentNotificationRepository commentNotificationRepository;
    private final AdoptNotificationRepository adoptNotificationRepository;

    public List<NotificationDto> getNotificationsByUserId(String userId) {
        List<NotificationDto> liked = likedNotificationRepository.findByCommentUserId_UserId(userId)
                .stream()
                .map(NotificationDto::fromLikedEntity)
                .collect(Collectors.toList());

        List<NotificationDto> comments = commentNotificationRepository.findByPostUserId_UserId(userId)
                .stream()
                .map(NotificationDto::fromCommentEntity)
                .collect(Collectors.toList());

        List<NotificationDto> adopts = adoptNotificationRepository.findByCommentUserId_UserId(userId)
                .stream()
                .map(NotificationDto::fromAdoptEntity)
                .collect(Collectors.toList());

        liked.addAll(comments);
        liked.addAll(adopts);

        return liked;
    }
}

