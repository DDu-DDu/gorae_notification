package com.gorae.gorae_notification.dto;

import com.gorae.gorae_notification.entity.notification.AdoptEntity;
import com.gorae.gorae_notification.entity.notification.CommentEntity;
import com.gorae.gorae_notification.entity.notification.LikedEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NotificationDto {
    private String type;
    private String message;
    private boolean isRead;
    private LocalDateTime readAt;

    public static NotificationDto fromLikedEntity(LikedEntity entity) {
        return NotificationDto.builder()
                .type("like")
                .message(entity.getCommentLikeUserId().getUserName() + "님이 댓글에 좋아요를 눌렀습니다.")
                .isRead(entity.getIsRead())
                .readAt(entity.getReadAt())
                .build();
    }

    public static NotificationDto fromCommentEntity(CommentEntity entity) {
        return NotificationDto.builder()
                .type("comment")
                .message(entity.getCommentUserId().getUserName() + "님이 게시글에 댓글을 남겼습니다.")
                .isRead(entity.getIsRead())
                .readAt(entity.getReadAt())
                .build();
    }

    public static NotificationDto fromAdoptEntity(AdoptEntity entity) {
        return NotificationDto.builder()
                .type("adopt")
                .message("당신의 댓글이 채택되었습니다!")
                .isRead(entity.getIsRead())
                .readAt(entity.getReadAt())
                .build();
    }
}