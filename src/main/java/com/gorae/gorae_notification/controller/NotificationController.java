package com.gorae.gorae_notification.controller;

import com.gorae.gorae_notification.dto.NotificationDto;
import com.gorae.gorae_notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/{userId}")
    public List<NotificationDto> getNotifications(@PathVariable String userId) {
        return notificationService.getNotificationsByUserId(userId);
    }
}