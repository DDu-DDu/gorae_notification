package com.gorae.gorae_notification.repository;

import com.gorae.gorae_notification.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository
    extends JpaRepository <UserEntity, Long> {
}
