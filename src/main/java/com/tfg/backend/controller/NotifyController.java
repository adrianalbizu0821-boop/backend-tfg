package com.tfg.backend.controller;

import com.tfg.backend.dto.NotificationRequest;
import com.tfg.backend.service.FcmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notify")
@CrossOrigin("*")
public class NotifyController {

    private final FcmService fcmService;

    public NotifyController(FcmService fcmService) {
        this.fcmService = fcmService;
    }

    @PostMapping("/{token}")
    public ResponseEntity<String> sendNotification(
            @PathVariable String token,
            @RequestBody NotificationRequest request)
            throws Exception {

        String response =
                fcmService.sendToToken(
                        token,
                        request.getTitle(),
                        request.getBody());

        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<String> sendToAll(
            @RequestBody NotificationRequest request) {

        fcmService.sendToAll(
                request.getTitle(),
                request.getBody());

        return ResponseEntity.ok(
                "Notificaciones enviadas");
    }
}