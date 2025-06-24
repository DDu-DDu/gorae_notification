package com.gorae.gorae_notification.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.gorae.gorae_notification.dto.NotificationDto;
import com.gorae.gorae_notification.repository.AdoptNotificationRepository;
import com.gorae.gorae_notification.repository.CommentNotificationRepository;
import com.gorae.gorae_notification.repository.LikedNotificationRepository;

import java.time.LocalDateTime;
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
                .toList();

        List<NotificationDto> adopts = adoptNotificationRepository.findByCommentUserId_UserId(userId)
                .stream()
                .map(NotificationDto::fromAdoptEntity)
                .toList();

        liked.addAll(comments);
        liked.addAll(adopts);

        return liked;
    }

    public void markAsRead(String type, Long id, String userId) {
        switch (type) {
            case "like" -> likedNotificationRepository.findById(id).ifPresent(n -> {
                if (n.getCommentUserId().getUserId().equals(userId)) {
                    n.setIsRead(true);
                    n.setReadAt(LocalDateTime.now());
                    likedNotificationRepository.save(n);
                }
            });
            case "comment" -> commentNotificationRepository.findById(id).ifPresent(n -> {
                if (n.getPostUserId().getUserId().equals(userId)) {
                    n.setIsRead(true);
                    n.setReadAt(LocalDateTime.now());
                    commentNotificationRepository.save(n);
                }
            });
            case "adopt" -> adoptNotificationRepository.findById(id).ifPresent(n -> {
                if (n.getCommentUserId().getUserId().equals(userId)) {
                    n.setIsRead(true);
                    n.setReadAt(LocalDateTime.now());
                    adoptNotificationRepository.save(n);
                }
            });
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        }
    }

    public void markAllAsRead(String userId) {
        likedNotificationRepository.findByCommentUserId_UserId(userId).forEach(n -> {
            n.setIsRead(true);
            n.setReadAt(LocalDateTime.now());
        });
        commentNotificationRepository.findByPostUserId_UserId(userId).forEach(n -> {
            n.setIsRead(true);
            n.setReadAt(LocalDateTime.now());
        });
        adoptNotificationRepository.findByCommentUserId_UserId(userId).forEach(n -> {
            n.setIsRead(true);
            n.setReadAt(LocalDateTime.now());
        });

        likedNotificationRepository.flush();
        commentNotificationRepository.flush();
        adoptNotificationRepository.flush();
    }
}

