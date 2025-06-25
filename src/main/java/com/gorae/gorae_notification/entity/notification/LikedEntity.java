package com.gorae.gorae_notification.entity.notification;

import com.gorae.gorae_notification.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "liked")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_userid")
    private UserEntity commentUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "like_userid")
    private UserEntity commentLikeUserId;

    private String message;

    @Builder.Default
    private Boolean isRead = false;

    private LocalDateTime readAt;
}
