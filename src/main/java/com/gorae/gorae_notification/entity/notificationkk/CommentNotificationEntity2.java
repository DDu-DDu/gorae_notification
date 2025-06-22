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
//public class CommentNotificationEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "")
//    private Question question;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "")
//    private Comment comment;
//
//    private String message;
//
//    private boolean isRead = false;
//
//    private LocalDateTime readAt;
//}
