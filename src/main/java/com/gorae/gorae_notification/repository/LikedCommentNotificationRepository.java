package com.gorae.gorae_notification.repository;

import com.gorae.gorae_notification.entity.notification.LikedCommentNotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikedCommentNotificationRepository
        extends JpaRepository<LikedCommentNotificationEntity, Long> {
}
