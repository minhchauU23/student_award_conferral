package com.devptit.confer_award_student_app.repository;

import com.devptit.confer_award_student_app.entity.LogTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogTaskRepository extends JpaRepository<LogTask, String > {
}
