package dev.ptit.notification_app.controller;

import dev.ptit.notification_app.dto.request.SendNotificationRequest;
import dev.ptit.notification_app.dto.response.APIResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/notifications_app")
public class Notification {
    @PostMapping("/notification")
    public APIResponse<Boolean> sendNotification(@RequestBody SendNotificationRequest request){
        return APIResponse.<Boolean>builder()
                .code(200)
                .message("successs")
                .result(true)
                .build();
    }
}
