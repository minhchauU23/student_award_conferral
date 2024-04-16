package com.devptit.student_app.controller;

import com.devptit.student_app.dto.request.StudentAwardUpdateRequest;
import com.devptit.student_app.dto.request.StudentTranscriptCreateRequest;
import com.devptit.student_app.dto.response.APIResponse;
import com.devptit.student_app.dto.response.StudentTranscriptResponse;
import com.devptit.student_app.service.StudentTranscriptService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student_transcript")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentTranscript {
    StudentTranscriptService studentTranscriptService;
    @GetMapping("/{studentID}")
    public APIResponse<StudentTranscriptResponse> getTranscript(@PathVariable("studentID") String studentId){
        return APIResponse.<StudentTranscriptResponse>builder()
                .code(200)
                .result(studentTranscriptService.getStudentTranscript(studentId))
                .build();
    }
//    @GetMapping()
//    public ApiResponse<StudentTranScriptResponse> getTranscript(@PathVariable String studentId){
//        return ApiResponse.<StudentTranScriptResponse>builder()
//                .code(200)
//                .result(studentTranscriptService.getStudentTranscript(studentId))
//                .build();
//    }
    @PostMapping
    public APIResponse<StudentTranscriptResponse> insert(@RequestBody StudentTranscriptCreateRequest request){
        return APIResponse.<StudentTranscriptResponse>builder()
                .code(200)
                .result(studentTranscriptService.createRequest(request))
                .build();
    }
    @PostMapping("/{studentID}")
    public APIResponse<StudentTranscriptResponse> updateStudentAward(@PathVariable("studentID") String studentID, @RequestBody StudentAwardUpdateRequest request){

        return APIResponse.<StudentTranscriptResponse>builder()
                .code(200)
                .result(studentTranscriptService.updateStudentAward(studentID, request))
                .build();

    }
}
