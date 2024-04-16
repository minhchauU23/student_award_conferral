package com.devptit.student_app.repository;

import com.devptit.student_app.entity.StudentTranscript;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentTranscriptRepository extends JpaRepository<StudentTranscript, String> {
}
