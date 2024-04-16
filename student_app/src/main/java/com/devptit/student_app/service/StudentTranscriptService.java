package com.devptit.student_app.service;

import com.devptit.student_app.dto.request.StudentAwardUpdateRequest;
import com.devptit.student_app.dto.request.StudentTranscriptCreateRequest;
import com.devptit.student_app.dto.response.StudentTranscriptResponse;
import com.devptit.student_app.entity.StudentAward;
import com.devptit.student_app.entity.StudentTranscript;
import com.devptit.student_app.exception.AppException;
import com.devptit.student_app.exception.ErrorCode;
import com.devptit.student_app.mapper.StudentTranscriptMapper;
import com.devptit.student_app.repository.StudentAwardRepository;
import com.devptit.student_app.repository.StudentTranscriptRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class StudentTranscriptService {
    StudentTranscriptRepository studentTranscriptRepository;
    StudentTranscriptMapper mapper;
    public StudentTranscriptResponse getStudentTranscript(String id){
        StudentTranscript studentTranscript = studentTranscriptRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.KEY_INVALID));
        log.error(studentTranscript.getStudentAwards().size()+"");
        return mapper.toStudentTranScriptResponse(studentTranscript);
    }

    public StudentTranscriptResponse createRequest(StudentTranscriptCreateRequest request) {
        StudentTranscript studentTranscript =  mapper.toStudentTranscript(request);
        return mapper.toStudentTranScriptResponse(studentTranscriptRepository.save(studentTranscript));
    }

    public StudentTranscriptResponse updateStudentAward(String studentID,StudentAwardUpdateRequest request){
        StudentTranscript studentTranscript = studentTranscriptRepository.findById(studentID)
                .orElseThrow(() -> new AppException(ErrorCode.KEY_INVALID));
        StudentAward studentAward = mapper.toStudentAward(request);
        studentTranscript.getStudentAwards().add(studentAward);
        studentTranscript = studentTranscriptRepository.save(studentTranscript);
        return mapper.toStudentTranScriptResponse(studentTranscript);

    }
}
