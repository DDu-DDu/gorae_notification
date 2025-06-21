package com.gorae.gorae_notification.entity.notification;

import com.gorae.gorae_notification.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostNotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_userid")
    private UserEntity userId;

    private String postTitle;

    private String postContent;
}
