package com.devptit.confer_award_student_app.mapper;

import com.devptit.confer_award_student_app.dto.request.TaskCreateRequest;
import com.devptit.confer_award_student_app.dto.response.LogTaskResponse;
import com.devptit.confer_award_student_app.dto.response.TaskResponse;
import com.devptit.confer_award_student_app.entity.LogTask;
import com.devptit.confer_award_student_app.entity.Task;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task toTask(TaskCreateRequest request);
    TaskResponse toTaskResponse(Task task);

    LogTaskResponse toLogTaskResponse(LogTask logTask);
    Set<LogTaskResponse> toLogTaskResponses(Set<LogTask> logTasks);
}
