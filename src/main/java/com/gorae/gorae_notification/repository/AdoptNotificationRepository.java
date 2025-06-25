package com.gorae.gorae_notification.repository;

import com.gorae.gorae_notification.entity.notification.AdoptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdoptNotificationRepository
    extends JpaRepository <AdoptEntity, Long> {
    List<AdoptEntity> findByCommentUserId_UserId(String userId);
}
