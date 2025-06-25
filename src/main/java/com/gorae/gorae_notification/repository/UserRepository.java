package com.gorae.gorae_notification.repository;

import com.gorae.gorae_notification.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository
    extends JpaRepository <UserEntity, Long> {

    Optional<UserEntity> findByUserId(String UserId);
}
