package com.gorae.gorae_notification.entity.notification;

import com.gorae.gorae_notification.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "adopt")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdoptEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_userid")
    private UserEntity postUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_userid")
    private UserEntity commentUserId;

    private String message;

    @Builder.Default
    private Boolean isRead = false;

    private LocalDateTime readAt;
}
