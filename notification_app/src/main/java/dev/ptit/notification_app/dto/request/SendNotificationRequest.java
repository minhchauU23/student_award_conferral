package dev.ptit.notification_app.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SendNotificationRequest {
    private String recipient;
    private String msgBody;
    private String subject;
}
