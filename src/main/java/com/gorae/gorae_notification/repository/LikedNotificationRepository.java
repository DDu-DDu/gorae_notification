package com.gorae.gorae_notification.repository;

import com.gorae.gorae_notification.entity.notification.LikedNotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikedNotificationRepository
        extends JpaRepository<LikedNotificationEntity, Long> {

}
