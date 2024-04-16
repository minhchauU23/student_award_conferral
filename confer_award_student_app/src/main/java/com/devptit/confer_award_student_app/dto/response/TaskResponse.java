package com.devptit.confer_award_student_app.dto.response;

import com.devptit.confer_award_student_app.entity.LogTask;
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
