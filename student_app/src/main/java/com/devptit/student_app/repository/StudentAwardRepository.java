package com.devptit.student_app.repository;

import com.devptit.student_app.entity.StudentAward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentAwardRepository extends JpaRepository<StudentAward, String> {
}
