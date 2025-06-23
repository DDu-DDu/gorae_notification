//package com.gorae.gorae_notification.dto;
//
//import com.gorae.gorae_notification.entity.notification.LikedNotificationEntity;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//
//import java.time.LocalDateTime;
//
//@Getter
//@Builder
//@AllArgsConstructor
//public class LikedNotificationDto {
//    private String type;
//    private String message;
//    private boolean isRead;
//    private LocalDateTime readAt;
//
//    public static LikedNotificationDto fromEntity(LikedNotificationEntity entity) {
//        return LikedNotificationDto.builder()
//                .type("like")
//                .message(entity.getMessage())
//                .isRead(entity.isRead())
//                .readAt(entity.getReadAt())
//                .build();
//    }
//}
