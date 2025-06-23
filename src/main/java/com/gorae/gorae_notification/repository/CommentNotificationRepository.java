package com.gorae.gorae_notification.repository;

import com.gorae.gorae_notification.entity.notification.CommentNotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentNotificationRepository
    extends JpaRepository<CommentNotificationEntity, Long> {
    List<CommentNotificationEntity> findByPostUserId_UserId(String userId);
}
