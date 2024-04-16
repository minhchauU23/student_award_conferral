package com.devptit.confer_award_student_app.controller;

import com.devptit.confer_award_student_app.dto.request.TaskCreateRequest;
import com.devptit.confer_award_student_app.dto.response.APIResponse;
import com.devptit.confer_award_student_app.dto.response.TaskResponse;
import com.devptit.confer_award_student_app.service.TaskService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/task")
public class StudentConferAward {
    TaskService taskService;

    @PostMapping
    public APIResponse<TaskResponse> start(@RequestBody TaskCreateRequest request){
        return APIResponse.<TaskResponse>builder()
                .code(200)
                .result(taskService.start(request))
                .build();
    }

    @GetMapping("/{taskID}")
    public APIResponse<TaskResponse> getTask(@PathVariable("taskID") String taskID){
        return APIResponse.<TaskResponse>builder()
                .code(200)
                .result(taskService.getTask(taskID))
                .build();
    }
}
