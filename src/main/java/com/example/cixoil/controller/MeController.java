package com.example.cixoil.controller;

import com.example.cixoil.dto.auth.AuthUserDTO;
import com.example.cixoil.dto.notification.NotificationDTO;
import com.example.cixoil.dto.notification.UnreadNotificationsCountDTO;
import com.example.cixoil.dto.queries.ActionResultDTO;
import com.example.cixoil.service.NotificationService;
import com.example.cixoil.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/me")
public class MeController {

    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<?> me(@AuthenticationPrincipal AuthUserDTO authUserDTO){
        return ResponseUtil.ok("Datos propios encontrados exitosamente", authUserDTO);
    }

    @GetMapping("/notifications")
    public ResponseEntity<?> getUserNotifications(@AuthenticationPrincipal AuthUserDTO authUserDTO, Pageable pageable) {
        Page<NotificationDTO> notifications = notificationService.getUserNotifications(authUserDTO.id(), pageable);
        return ResponseUtil.ok("Notificaciones encontradas exitosamente", notifications);
    }

    @PatchMapping("/notifications/read-all")
    public ResponseEntity<?> readAllNotifications(@AuthenticationPrincipal AuthUserDTO authUserDTO) {
        int notificationsRead = notificationService.updateAllStatus(authUserDTO.id());
        return ResponseUtil.ok(
                notificationsRead > 0
                        ? "Notificaciones marcadas como leídas"
                        : "No hay más notificaciones por leer",
                new ActionResultDTO(notificationsRead > 0, notificationsRead));
    }

    @PatchMapping("/notifications/read/{id}")
    public ResponseEntity<?> readNotification(
            @AuthenticationPrincipal AuthUserDTO authUserDTO,
            @PathVariable Long id

    ) {
        boolean notificationRead = notificationService.updateStatus(id, authUserDTO.id());
        return ResponseUtil.ok(
                notificationRead
                        ? "Notificación leída correctamente"
                        : "No se pudo marcar la notificación",
                new ActionResultDTO(notificationRead, notificationRead ? 1 : 0));
    }

    @GetMapping("/notifications/count/unread")
    public ResponseEntity<?> countUnreadNotifications(
            @AuthenticationPrincipal AuthUserDTO authUserDTO
    ) {
        long unreadNotifications = notificationService.countUnread(authUserDTO.id());
        return ResponseUtil.ok(
                (unreadNotifications > 0)
                        ? "Hay mensajes no leídos"
                        : "No hay mensajes sin leer",
                new UnreadNotificationsCountDTO(unreadNotifications)
        );
    }
}
