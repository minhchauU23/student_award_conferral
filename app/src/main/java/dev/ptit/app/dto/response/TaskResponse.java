package dev.ptit.app.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {
    String id;
    String studentID;
    String eventId;
    String awardId;
    Set<LogTaskResponse> logTasks;
}
