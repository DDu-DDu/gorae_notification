//package com.gorae.gorae_notification.entity.notificationkk;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class LikedNotificationEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "")
//    private Comment comment;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "")
//    private Like like;
//
//    private String message;
//
//    private boolean isRead = false;
//
//    private LocalDateTime readAt;
//}
