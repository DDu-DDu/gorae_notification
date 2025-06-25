package com.gorae.gorae_notification.repository;

import com.gorae.gorae_notification.entity.notification.LikedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikedNotificationRepository
        extends JpaRepository<LikedEntity, Long> {
        List<LikedEntity> findByCommentUserId_UserId(String userId);
}
