//package com.gorae.gorae_notification.dto;
//
//import com.gorae.gorae_notification.entity.notification.CommentNotificationEntity;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//
//import java.time.LocalDateTime;
//
//@Getter
//@Builder
//@AllArgsConstructor
//public class CommentNotificationDto {
//    private String type;
//    private String message;
//    private boolean isRead;
//    private LocalDateTime readAt;
//
//    public static CommentNotificationDto fromEntity(CommentNotificationEntity entity) {
//        return CommentNotificationDto.builder()
//                .type("adopt")
//                .message(entity.getMessage())
//                .isRead(entity.isRead())
//                .readAt(entity.getReadAt())
//                .build();
//    }
//}
