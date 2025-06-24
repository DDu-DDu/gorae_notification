package com.gorae.gorae_notification.controller;

import com.gorae.gorae_notification.dto.NotificationDto;
import com.gorae.gorae_notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    // 🔐 토큰 필요 (유저 정보는 @AuthenticationPrincipal에서 추출)
    @GetMapping("/{userId}")
    public List<NotificationDto> getNotifications(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user) {
        return notificationService.getNotificationsByUserId(user.getUsername());
    }

    @PostMapping("/{id}/{type}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable String type,
                                           @PathVariable Long id,
                                           @AuthenticationPrincipal org.springframework.security.core.userdetails.User user) {
        notificationService.markAsRead(type, id, user.getUsername());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}/read-all")
    public ResponseEntity<Void> markAllAsRead(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user) {
        notificationService.markAllAsRead(user.getUsername());
        return ResponseEntity.ok().build();
    }

//    // 🔓 테스트용 - userId 직접 입력
//    @GetMapping("/{userId}")
//    public List<NotificationDto> getNotifications(@PathVariable String userId) {
//        return notificationService.getNotificationsByUserId(userId);
//    }
//
//    @PostMapping("/{userId}/{type}/read")
//    public ResponseEntity<Void> markAsRead(@PathVariable String userId,
//                                           @PathVariable String type,
//                                           @PathVariable Long id) {
//        notificationService.markAsRead(type, id, userId);
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/{userId}/read-all")
//    public ResponseEntity<Void> markAllAsRead(@PathVariable String userId) {
//        notificationService.markAllAsRead(userId);
//        return ResponseEntity.ok().build();
//    }
}