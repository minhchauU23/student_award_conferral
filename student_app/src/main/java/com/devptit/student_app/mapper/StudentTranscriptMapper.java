package com.devptit.student_app.mapper;

import com.devptit.student_app.dto.request.StudentAwardUpdateRequest;
import com.devptit.student_app.dto.request.StudentTranscriptCreateRequest;
import com.devptit.student_app.dto.response.StudentAwardResponse;
import com.devptit.student_app.dto.response.StudentTranscriptResponse;
import com.devptit.student_app.entity.StudentAward;
import com.devptit.student_app.entity.StudentTranscript;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface StudentTranscriptMapper {

    StudentAwardResponse toStudentAwardResponse(StudentAward studentAward);
    StudentAward toStudentAward(StudentAwardUpdateRequest request);
    Set<StudentAwardResponse> toStudentAwardResponses(Set<StudentAward> studentAwards);
    StudentTranscriptResponse toStudentTranScriptResponse(StudentTranscript studentTranscript);
    StudentTranscript toStudentTranscript(StudentTranscriptCreateRequest request);
}
