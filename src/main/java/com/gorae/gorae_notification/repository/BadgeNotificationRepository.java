package com.gorae.gorae_notification.repository;

import com.gorae.gorae_notification.entity.notification.BadgeNotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadgeNotificationRepository
    extends JpaRepository<BadgeNotificationEntity, Long> {
}
